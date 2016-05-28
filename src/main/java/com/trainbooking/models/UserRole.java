package com.trainbooking.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

public class UserRole implements GrantedAuthority,Serializable {
    private String name;

    public UserRole(){

    }

    public UserRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UserRole))
            return false;
        UserRole role = (UserRole)obj;
        return role.getName().equals(getName());
    }
}
