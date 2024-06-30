package com.playcentric.model.game.secondary;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@Entity @Table(name = "gameWishlist")
@IdClass(GameAndMemId.class)
public class GameWishlist {
	
	@Id
	private int gameId;
	
	@Id
	private int memId;
	
	private LocalDateTime addAt;
	
}
