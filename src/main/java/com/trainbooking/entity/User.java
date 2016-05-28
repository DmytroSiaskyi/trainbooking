package com.trainbooking.entity;

import com.trainbooking.dto.UserDto;
import com.trainbooking.models.UserRole;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cashier")
public class User implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(length = 64, nullable=false, unique = true)
    private String login;

    @Column(length = 64, nullable=false)
    private String password;

    @Column(nullable=false)
    private String role;

    @Column(name="first_name", length = 128, nullable=false)
    private String firstName;

    @Column(name="last_name", length = 128, nullable=false)
    private String lastName;
    @Transient
    private List<UserRole> roles;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;

    }

    public List<UserRole> getRoles() {
        roles = new ArrayList<>();
        roles.add(new UserRole(role));
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static User create(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole().getName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }

    public UserDto toDto(){
        UserDto user = new UserDto();
        user.setId(this.id);
        user.setUsername(this.login);
        user.setPassword(this.password);
        user.setRole(new UserRole(this.role));
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        return user;
    }
}
