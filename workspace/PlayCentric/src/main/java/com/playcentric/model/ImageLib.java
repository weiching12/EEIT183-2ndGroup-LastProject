package com.playcentric.model;

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
@Entity @Table(name = "imageLib")
public class ImageLib {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imageId;
	
	private byte[] imageFile;
	
}
