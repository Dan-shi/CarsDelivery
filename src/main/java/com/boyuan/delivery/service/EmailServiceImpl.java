/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.configuration.CommonConfigProperties;
import com.boyuan.delivery.model.Order;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * 使用JavaMail发送邮件的5个步骤:
 * 1. 创建定义整个应用程序所需的环境信息的 Session 对象
 * 2、通过session得到transport对象
 * 3、使用邮箱的用户名和授权码连上邮件服务器
 * 4、创建邮件
 * 5、发送邮件
 */
@Service
public class EmailServiceImpl implements EmailService {
    protected final Log logger = LogFactory.getLog(EmailServiceImpl.class);

    /**
     * Email session
     */
    private Session session;

    private String sender;

    private List<String> recipients;

    private String authCode;

    public EmailServiceImpl(CommonConfigProperties configProperties) {
        this.sender = configProperties.getEmailSenderAddress();
        this.authCode = configProperties.getEmailAuthCode();
        this.recipients = Arrays.asList(configProperties.getEmailRecipientsAddress().split(","));
        this.buildEmailSession();
    }

    /**
     * Build email session, for reuse purpose
     */
    private void buildEmailSession() {
        //Create session
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); //// 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码
        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e) {
            this.logger.error("Error creating email session", e);
        }
        //1. 创建定义整个应用程序所需的环境信息的 Session 对象
        this.session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);

    }

    /**
     * Send email when there is a order generated
     *
     * @param order
     */
    @Override
    public void sendOrderEmail(Order order) {
        Transport ts = null;
        try {
            //2、通过session得到transport对象
            ts = session.getTransport();

            //3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect("smtp.qq.com", this.sender, this.authCode);

            //4、创建邮件
            //创建邮件对象
            MimeMessage message = createEmailMessage(this.sender, this.recipients, "只包含文本的简单邮件", "你好啊！");
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());

        } catch (Exception e) {
            logger.error("Send email error. Order customer phone : " + order.getCusPhone(), e);
        } finally {
            if (ts != null) {
                try {
                    ts.close();
                } catch (MessagingException e) {
                    logger.error("Close email transport error, phone number: " + order.getCusPhone(), e);
                }
            }
        }
    }

    /**
     * Create email message
     *
     * @param sendMail     from who
     * @param receiveMails recipients
     * @param subject
     * @param messageBody
     * @return
     * @throws MessagingException
     */
    private MimeMessage createEmailMessage(String sendMail, List<String> receiveMails, String subject, String messageBody) throws MessagingException {
        //创建邮件对象
        MimeMessage message = new MimeMessage(this.session);

        if (CollectionUtils.isEmpty(receiveMails)) {
            return message;
        }

        List<InternetAddress> recipients = new ArrayList<>();
        recipients.addAll(receiveMails
                .stream()
                .map(email -> {
                    try {
                        return new InternetAddress(email);
                    } catch (AddressException e) {
                        logger.error("Create email recipient error", e);
                        return new InternetAddress();
                    }
                })
                .collect(Collectors.toSet()));
        //指明邮件的发件人
        message.setFrom(new InternetAddress(sendMail));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipients(Message.RecipientType.TO, (InternetAddress[]) recipients.toArray());
        //邮件的标题
        message.setSubject(subject);
        //邮件的文本内容
        message.setContent(messageBody, "text/html;charset=UTF-8");
        return message;
    }
}
