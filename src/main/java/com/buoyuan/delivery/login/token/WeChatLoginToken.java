package com.buoyuan.delivery.login.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class WeChatLoginToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 3981518947978678754L;

    private String code;
    private DecodedJWT token;
    private UserDetails principal;
    //@todo connect to wechat server
//    String openId

    public WeChatLoginToken(String code){
        super(null);
        this.code = code;
        this.token = null;
        this.principal = null;
        this.setAuthenticated(false);
    }

    public WeChatLoginToken(String code, UserDetails principal, DecodedJWT token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.code = code;
        this.token = token;
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.code;
    }

    @Override
    public Object getPrincipal() {
        return this.code;
    }

    public String getCode() {
        return code;
    }

    public DecodedJWT getToken() {
        return token;
    }
}
