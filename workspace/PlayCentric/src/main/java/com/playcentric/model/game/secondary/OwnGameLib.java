package com.playcentric.model.game.secondary;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@Entity @Table(name = "ownGameLib")
@IdClass(GameAndMemId.class)
public class OwnGameLib {
	
	@Id
	private int memId;
	
	@Id
	private int gameId;
	
	private LocalDateTime buyAt;
	
}
