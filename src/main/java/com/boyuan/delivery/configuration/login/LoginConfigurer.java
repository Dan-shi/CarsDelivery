package com.boyuan.delivery.configuration.login;

import com.boyuan.delivery.login.filter.NormalUsernamePasswordAuthenticationFilter;
import com.boyuan.delivery.login.handler.LoginFailureHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

public class LoginConfigurer<T extends LoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {

	private NormalUsernamePasswordAuthenticationFilter authFilter;

	public LoginConfigurer() {
		this.authFilter = new NormalUsernamePasswordAuthenticationFilter();
	}
	
	@Override
	public void configure(B http) throws Exception {
		//设置Filter使用的AuthenticationManager,这里取公共的即可
		authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		//设置失败的Handler
		authFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
		//不将认证后的context放入session
		authFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

		NormalUsernamePasswordAuthenticationFilter filter = postProcess(authFilter);
		//指定Filter的位置
		http.addFilterAfter(filter, LogoutFilter.class);
	}

	//设置成功的Handler，这个handler定义成Bean，所以从外面set进来
	public LoginConfigurer<T,B> loginSuccessHandler(AuthenticationSuccessHandler authSuccessHandler){
		authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
		return this;
	}

}
