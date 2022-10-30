package com.example.zuulApigateway.zuulApigateway.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "User_Details")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private Integer userId;

    @Column(name="userName")
    private String userName;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="emailId")
    private String emailId;

    @Column(name="password")
    private String password;

    @Column(name="confirmPassword")
    private String confirmPassword;


}
