package com.boyuan.delivery.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.boyuan.delivery.model.AdminUser;
import com.boyuan.delivery.model.BynUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Since webSecurity cannot autowire beans, if we add cacheable annotation to any method in this class, bean conflict will occur.
 * So move all cache methods to UserService
 */
@Service
public class JwtUserService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AdminUserService adminUserService;

	@Value("${boyuan.login.tokenExpireTime}")
	private long tokenExpireTime;

	public UserDetails getUserLoginInfo(String username) {
		String salt = this.userService.getSalt();
    	/**
    	 * @todo 从数据库或者缓存中取出jwt token生成时用的salt
    	 * salt = redisTemplate.opsForValue().get("token:"+username);
    	 */   	
    	UserDetails user = loadUserByUsername(username);
    	//将salt放到password字段返回
    	return User.builder().username(user.getUsername()).password(salt).authorities(user.getAuthorities()).build();
	}
	
	public String saveUserLoginInfo(UserDetails user) {
		String salt = this.userService.getSalt(); //BCrypt.gensalt();  正式开发时可以调用该方法实时生成加密的salt
		/**
    	 * @todo 将salt保存到数据库或者缓存中
    	 * redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
    	 */   	
		Algorithm algorithm = Algorithm.HMAC256(salt);
		Date date = new Date(System.currentTimeMillis()+tokenExpireTime*1000);  //设置1小时后过期
        return JWT.create()
        		.withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
	}

	/**
	 * User by DaoAuthenticationProvider to load user information
	 * DaoAuthenticationProvider is implement authenticate method for AuthenticationManager
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BynUser user = this.userService.getUserByUserName(username);
		return User.builder().username(user.getUserName()).password(user.getPassword()).roles("USER").build();
	}

	public UserDetails loadAdminUserByUsername(String username) {
		AdminUser user = this.adminUserService.getAdminUserByName(username);
		return User.builder().username(user.getAdUserName()).password(user.getPassword()).roles("USER").build();
	}

	public void deleteUserLoginInfo(String username) {
		this.userService.deleteUserLoginInfo(username);
	}
}
