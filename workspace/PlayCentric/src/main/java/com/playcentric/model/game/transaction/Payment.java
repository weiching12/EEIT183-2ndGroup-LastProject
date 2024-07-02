package com.playcentric.model.game.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity @Table(name = "payment")
public class Payment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;
	private String paymentName;
	
}
