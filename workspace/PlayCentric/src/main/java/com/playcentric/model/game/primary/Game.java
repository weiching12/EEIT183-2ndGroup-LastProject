package com.playcentric.model.game.primary;

import java.time.LocalDateTime;
import java.util.List;

import com.playcentric.model.ImageLib;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameId;
	
	private String gameName;
	
	private int price;
	
	private String description;
	
	private LocalDateTime releaseAt;
	
	private String developer;
	
	private String publisher;
	
	private String gameFilePath;
	
	private boolean isShow = true;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "gameTypes",
	joinColumns = @JoinColumn(name ="gameId"),
	inverseJoinColumns = @JoinColumn(name = "gameTypeId"))
	private List<GameTypeLib> gameTypeLibs;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "gameImages",
	joinColumns = @JoinColumn(name ="gameId"),
	inverseJoinColumns = @JoinColumn(name = "imageId"))
	private List<ImageLib> imageLibs;
	
}
