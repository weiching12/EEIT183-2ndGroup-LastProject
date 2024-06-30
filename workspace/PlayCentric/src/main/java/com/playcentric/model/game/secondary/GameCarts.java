package com.playcentric.model.game.secondary;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@Entity @Table(name = "gameCarts")
@IdClass(GameAndMemId.class)
public class GameCarts {
	
	@Id
	private int gameId;
	
	@Id
	private int memId;
	
	private int amount;
	
	private LocalDateTime addAt;
	
	private LocalDateTime updateAt;
	
}
