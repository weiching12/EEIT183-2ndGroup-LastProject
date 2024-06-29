package com.playcentric.model.game.transaction;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gameOrder")
public class GameOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameOrderId;
	
	private int memId;
	
	private LocalDateTime createAt;
	
	private LocalDateTime updateAt;
	
	private String status;
	
	private int paymentId;
	
	private int total;
	
	@ManyToOne @JoinColumn(name = "memId")
	private int member;
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
			mappedBy = "gameOrder")
	private int payment;
}
