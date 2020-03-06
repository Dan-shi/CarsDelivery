package com.buoyuan.delivery.login.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.buoyuan.delivery.login.token.WeChatLoginToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Login filter
 * Retrieve username and password from request body
 * Only execute on login behaviour
 */
public class WechatLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public WechatLoginAuthenticationFilter() {
		super(new AntPathRequestMatcher("/wechatLogin","POST"));
	}
	
	@Override
	public void afterPropertiesSet() {
		Assert.notNull(getAuthenticationManager(), "authenticationManager must be specified");
		Assert.notNull(getSuccessHandler(), "AuthenticationSuccessHandler must be specified");
		Assert.notNull(getFailureHandler(), "AuthenticationFailureHandler must be specified");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
		String code = null;
		if(StringUtils.hasText(body)) {
		    JSONObject jsonObj = JSON.parseObject(body);
			code = jsonObj.getString("code");
		}

		if (code == null)
			code = "";

		WeChatLoginToken authRequest = new WeChatLoginToken(code);

		//Use DaoAuthenticationProvider to do authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

}
