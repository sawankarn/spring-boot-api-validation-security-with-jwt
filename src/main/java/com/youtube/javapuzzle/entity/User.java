package com.youtube.javapuzzle.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int userId;
    @Column(name="user_name")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 10, message = "Username should be min 2 characters and max 10 characters")
    private String username;
    @NotNull
    @NotEmpty
    @Column(length = 100)
//    @Size(min = 2, max = 10, message = "Password should be min 2 characters and max 10 characters")
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;
}

