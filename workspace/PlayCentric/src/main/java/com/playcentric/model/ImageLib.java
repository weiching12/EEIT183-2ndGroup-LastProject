package com.playcentric.model;

import java.util.List;

import com.playcentric.model.game.primary.Game;
import com.playcentric.model.playfellow.ImageLibPfmemberAssociation;
import com.playcentric.model.playfellow.PlayFellowMember;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "imageLib")
public class ImageLib {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imageId;

	private byte[] imageFile;

	@ManyToMany(mappedBy = "imageLibs")
	private List<Game> games;

	@OneToMany(mappedBy = "imageLib")
	private List<ImageLibPfmemberAssociation> imageLibAssociations;

}
