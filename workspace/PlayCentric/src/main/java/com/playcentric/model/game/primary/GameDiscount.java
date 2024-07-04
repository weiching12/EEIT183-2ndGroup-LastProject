package com.playcentric.model.game.primary;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity @Table(name = "gameDiscount")
@IdClass(GameDiscountId.class)
public class GameDiscount {
	
	@Id
	private Integer gameDiscountId;
	@Id
	private Integer gameId;
	private BigDecimal discountRate;
	@ManyToOne
	@JoinColumn(name = "gameId")
	private Game game;
	@ManyToOne
	@JoinColumn(name = "gameDiscountId")
	private GameDiscountSet gameDiscountSet;
}
