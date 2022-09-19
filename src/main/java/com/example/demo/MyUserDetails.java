package com.example.demo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;
public class MyUserDetails implements UserDetails {
private User user;
    public MyUserDetails(User user) {
    super();
    this.user = user;
}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
         return Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return !(user.getIsAccountLocked());
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return user.getIsCredentialsNonExpired();
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-gen
    	return user.getIsEnabled();
    }
}
