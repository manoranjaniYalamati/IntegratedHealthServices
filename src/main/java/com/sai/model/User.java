package com.sai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String contactEmail;

    @Column
    private String phone;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private  Gender gender;

    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLES",
//            joinColumns = {
//                    @JoinColumn(name = "USER_ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "ROLE_ID") })
//    private Set<Role> roles;



}
