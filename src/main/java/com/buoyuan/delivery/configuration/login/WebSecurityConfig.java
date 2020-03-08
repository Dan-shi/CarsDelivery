package com.buoyuan.delivery.configuration.login;

import com.buoyuan.delivery.login.filter.OptionsRequestFilter;
import com.buoyuan.delivery.login.handler.JwtRefreshSuccessHandler;
import com.buoyuan.delivery.login.handler.LoginSuccessHandler;
import com.buoyuan.delivery.login.handler.TokenClearLogoutHandler;
import com.buoyuan.delivery.login.provider.JwtAuthenticationProvider;
import com.buoyuan.delivery.service.UserLogin.JwtUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//url 权限认证
//		        .antMatchers("/image/**").permitAll()
//		        .antMatchers("/admin/**").hasAnyRole("ADMIN")
//		        .antMatchers("/user/**").hasRole("USER")
		        .anyRequest().authenticated()
		        .and()
		    .csrf().disable()
		    .formLogin().disable()
		    .sessionManagement().disable()
		    .cors()
		    .and()
		    .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
		    		new Header("Access-control-Allow-Origin","*"),
		    		new Header("Access-Control-Expose-Headers","Authorization"))))
		    .and()
		    .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
		    .apply(new LoginConfigurer<>()).loginSuccessHandler(loginSuccessHandler())
		    .and()
		    .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler()).permissiveRequestUrls("/logout")
		    .and()
		    .logout()
//		        .logoutUrl("/logout")   //默认就是"/logout"
		        .addLogoutHandler(tokenClearLogoutHandler()) //add logout handler
		        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) //logout success handler
			.and()
			.sessionManagement().disable();
	}

	/**
	 * 	add provider to authentication manager
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(jwtAuthenticationProvider());
	}

	/**
	 * Inject default authentication manager
	 */
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

	/**
	 * Inject JwtAuthenticationProvider
	 */
	@Bean("jwtAuthenticationProvider")
	protected AuthenticationProvider jwtAuthenticationProvider() {
		return new JwtAuthenticationProvider(jwtUserService());
	}

	/**
	 * Inject daoAuthenticationProvider
	 * @return
	 * @throws Exception
	 */
	@Bean("daoAuthenticationProvider")
	protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
		//这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService());
		return daoProvider;
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return jwtUserService();
	}

	@Override
	public UserDetailsService userDetailsServiceBean() {
		return jwtUserService();
	}


	@Bean
	protected JwtUserService jwtUserService() {
		return new JwtUserService();
	}

	@Bean
	protected LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler(jwtUserService());
	}
	
	@Bean
	protected JwtRefreshSuccessHandler jwtRefreshSuccessHandler() {
		return new JwtRefreshSuccessHandler(jwtUserService());
	}
	
	@Bean
	protected TokenClearLogoutHandler tokenClearLogoutHandler() {
		return new TokenClearLogoutHandler(jwtUserService());
	}
	
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD", "OPTION"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
