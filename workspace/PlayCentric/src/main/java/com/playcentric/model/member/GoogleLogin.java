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
@Table(name = "googleLogin")
public class GoogleLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer googleId;
    private String name;
    private String photo;
    private String email;
}
