package com.buoyuan.delivery.login.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.buoyuan.delivery.login.token.JwtAuthenticationToken;
import com.buoyuan.delivery.login.token.WeChatLoginToken;
import com.buoyuan.delivery.service.UserLogin.JwtUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import java.util.Calendar;

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
