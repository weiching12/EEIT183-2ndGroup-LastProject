package com.playcentric.model.game.transaction;

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
@Entity @Table(name = "gameOrderDetails")
@IdClass(GameOrderDetailsId.class)
public class GameOrderDetails {
	
	@Id
	private int gameOrderId;
	
	@Id
	private int gameId;
	
	private int amount;
	
	private int unitPrice;
	
	private double discountRate;
	
}
