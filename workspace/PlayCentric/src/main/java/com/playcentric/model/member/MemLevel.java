package com.playcentric.model.member;

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
@Entity
@Table(name = "memLevel")
public class MemLevel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer levelId;
	private String level;
	private Integer minAmount;
	private Integer maxAmount;
}
