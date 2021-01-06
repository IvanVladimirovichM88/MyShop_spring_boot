package ru.geekbrains.entities;


import ru.geekbrains.data.UserData;
import ru.geekbrains.services.RoleService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_tbl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username_fld",unique = true)
    private String username;

    @Column(name = "password_fld")
    private String password;

    @Column(name = "name_fld")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_role_tbl",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////


    public User() {
    }

    public User(String name, String username, String password ) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void init(UserData userData){
        this.username = userData.getUsername();
        this.password = userData.getPassword();
        this.name = userData.getName();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
