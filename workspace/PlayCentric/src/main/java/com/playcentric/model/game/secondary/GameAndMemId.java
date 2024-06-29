package com.playcentric.model.game.secondary;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class GameAndMemId implements Serializable{

	private int gameId;
	
	private int memId;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
        GameAndMemId that = (GameAndMemId) obj;
        return Objects.equals(gameId, that.gameId) &&
               Objects.equals(memId, that.memId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gameId,memId);
	}
	
}
