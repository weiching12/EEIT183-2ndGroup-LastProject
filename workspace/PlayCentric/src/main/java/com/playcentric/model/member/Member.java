package com.playcentric.model.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memId;
    private String account;
    private String password;
    private String email;
    private String nickname;
    private String memName;
    private String birthday;
    private String phone;
    private String address;
    private String googeId;
    private String facebookId;
    private String twitterId;
    private String totalSpent;
    private String registDate;
    private String lastLogin;
    private String role;
    private String photo;
    private String gender;
    private String status;
    private String points;
}
