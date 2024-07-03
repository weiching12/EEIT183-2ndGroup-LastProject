package com.playcentric.model.game.secondary;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemAndGameReviewId implements Serializable{
	
	private Integer memId;
	
	private Integer gameReviewsId;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
        MemAndGameReviewId that = (MemAndGameReviewId) obj;
        return Objects.equals(gameReviewsId, that.gameReviewsId) &&
               Objects.equals(memId, that.memId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gameReviewsId,memId);
	}
	
}
