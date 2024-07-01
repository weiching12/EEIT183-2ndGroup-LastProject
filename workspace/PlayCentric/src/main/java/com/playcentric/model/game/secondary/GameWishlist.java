package com.playcentric.model.game.secondary;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.playcentric.model.game.primary.Game;
import com.playcentric.model.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity @Table(name = "gameWishlist")
@IdClass(GameAndMemId.class)
public class GameWishlist {
	
	@Id
	@Column(insertable = false,updatable = false)
	private Integer gameId;
	@Id
	@Column(insertable = false,updatable = false)
	private Integer memId;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy年MM月dd日 HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime addAt;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memId")
	private Member member;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gameId")
	private Game game;
	
}
