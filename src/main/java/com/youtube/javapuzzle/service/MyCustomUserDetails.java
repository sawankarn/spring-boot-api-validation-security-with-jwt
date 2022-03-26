package com.youtube.javapuzzle.service;

import com.youtube.javapuzzle.entity.Role;
import com.youtube.javapuzzle.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
public class MyCustomUserDetails implements UserDetails {

    private User user;

    public MyCustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet = user.getRoleSet();
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        for(Role role: roleSet){
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
