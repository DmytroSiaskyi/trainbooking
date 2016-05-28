package com.trainbooking.dto;

import com.trainbooking.entity.User;
import com.trainbooking.models.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class UserDto implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
    private List<UserRole> roles;

    private Boolean enabled = true;

    private ConcurrentHashMap<String,Object> CACHE;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @XmlTransient
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName==null?null:firstName.trim();
    }

    @XmlTransient
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName==null?null:lastName.trim();
    }

    public String getPassword() {
        return password;
    }

    @XmlTransient
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public String getStringRole(){
        for (UserRole r: roles){
            if (r.getName().equalsIgnoreCase("admin"))
                return "admin";
        }
        return "user";
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username==null?null:username.trim();
    }

    public void setPassword(String password) {
        this.password = password==null?null:password.trim();
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public static UserDto create(User user){
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole(user.getRole()));
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(new UserRole(user.getRole()));
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(new ArrayList<>());
        userDto.setEnabled(true);
        return userDto;
    }
    public static List<UserDto> create(List<User> users){
        List<UserDto> dtoList = new ArrayList<UserDto>();
        for (User u:users){
            dtoList.add(UserDto.create(u));
        }
        return dtoList;
    }

    public boolean hasRole(String role){
        int index = this.getRoles().indexOf(new UserRole(role));
        return index >= 0;
    }

    public boolean isNewUser() {
        return this.password == null;
    }


    public ConcurrentHashMap<String, Object> getCACHE() {
        return CACHE;
    }

    public void setCACHE(ConcurrentHashMap<String, Object> CACHE) {
        this.CACHE = CACHE;
    }
}
