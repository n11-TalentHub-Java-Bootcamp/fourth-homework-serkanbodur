package com.example.fourthhomework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
//@Table(name = "users")
public class User implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "SURNAME", length = 50)
    private String surname;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "PHONE", length = 15)
    private String phone;

    @Column(name = "username", length = 20)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Debt> debtList;

}
