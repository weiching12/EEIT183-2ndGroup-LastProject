package com.playcentric.model.member;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
    private Date birthday;
    @Column(columnDefinition = "CHAR(10)")
    private String phone;
    private String address;
    private String googeId;
    private String facebookId;
    private String twitterId;
    private Integer totalSpent;
    private Date registDate;
    private LocalDateTime lastLogin;
    private short role;
    private Integer photo;
    private short gender;
    private short status;
    private Integer points;
}
