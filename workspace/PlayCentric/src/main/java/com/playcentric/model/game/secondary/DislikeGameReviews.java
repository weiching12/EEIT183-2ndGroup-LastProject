package com.playcentric.model.game.secondary;

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
@Table(name = "dislikeGameReviews")
public class DislikeGameReviews {
	
	@Id
	private Integer gameReviewsId;
	@Id
	private Integer memId;
	@ManyToOne
	@JoinColumn(name = "memId")
	private Member member;
	@ManyToOne
	@JoinColumn(name = "gameReviewsId")
	private GameReviews gameReviews;
	
}
