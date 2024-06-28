package com.playcentric.model.game.primary;

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
@Entity @Table(name = "gameDiscounts")
@IdClass(GameDiscountId.class)
public class GameDiscount {
	
	@Id
	private int gameDiscountId;
	
	@Id
	private int gameId;
	
	private double discountRate;
}
