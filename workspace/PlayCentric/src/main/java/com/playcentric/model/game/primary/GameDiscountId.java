package com.playcentric.model.game.primary;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class GameDiscountId implements Serializable{
	
	private Integer gameId;
	
	private Integer gameDiscountId;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
        GameDiscountId that = (GameDiscountId) obj;
        return Objects.equals(gameId, that.gameId) &&
               Objects.equals(gameDiscountId, that.gameDiscountId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gameId,gameDiscountId);
	}
	
	
}
