package com.playcentric.model.game.transaction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@Entity @Table(name = "payment")
public class Payment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	
	private String paymentName;
	
	@OneToMany(mappedBy = "payment",fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,orphanRemoval = true)
	private Recharge recharge;
}
