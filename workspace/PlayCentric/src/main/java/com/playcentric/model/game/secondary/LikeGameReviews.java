package com.playcentric.model.game.secondary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.playcentric.model.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "likeGameReviews")
public class LikeGameReviews {
	
	@Id
	private Integer gameReviewsId;
	@Id
	private Integer memId;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "memId")
	private Member member;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "gameReviewsId")
	private GameReviews gameReviews;
	
	
}
