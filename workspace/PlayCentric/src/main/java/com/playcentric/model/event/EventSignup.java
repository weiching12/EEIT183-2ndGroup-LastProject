package com.playcentric.model.event;

import java.time.LocalDateTime;
import java.util.Date;

import com.playcentric.model.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eventSignup")
public class EventSignup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int signupId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;

    private LocalDateTime signupTime;

    private int workType;

    private String workTitle;

    private String workDescription;

    private Date workUploadTime;

    @Lob
    private byte[] work;

    private int voteCount;
}
