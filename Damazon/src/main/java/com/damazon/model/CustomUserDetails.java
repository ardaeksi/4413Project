package com.damazon.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;  

    // Constructor
    public CustomUserDetails(String id, String username, String password, boolean isAdmin) {
        this.id = Integer.parseInt(id);
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
    	
        // If the user is admin, return an authority indicating as such
        if (isAdmin) {
            return Collections.singletonList(() -> "ROLE_ADMIN");
        } else {
            return Collections.singletonList(() -> "ROLE_USER");
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    // Get ID
    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
