package com.boyuan.delivery.login.provider;

import com.boyuan.delivery.login.token.WeChatLoginToken;
import com.boyuan.delivery.service.JwtUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class wechatAuthenticationProvider implements AuthenticationProvider {

	private JwtUserService userService;

	public wechatAuthenticationProvider(JwtUserService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String code = ((WeChatLoginToken)authentication).getCode();

		//@todo get wechat login user info and generate token
//		if(jwt.getExpiresAt().before(Calendar.getInstance().getTime())){
//			throw new NonceExpiredException("Token expires");
//		}

		WeChatLoginToken token = new WeChatLoginToken(code);
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(WeChatLoginToken.class);
	}



}
