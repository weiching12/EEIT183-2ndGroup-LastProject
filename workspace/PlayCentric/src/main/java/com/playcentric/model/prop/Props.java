package com.playcentric.model.prop;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="props")
public class Props {
	
//	PK
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer propId;
    
	private String propName;
    private String propRarity;
    private String propDescription;
    private String propImageName;
    private Date createdTime;
    private Date updatedTime;

	
//  FK
//	private Game game;
    
//  FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProptypeId", referencedColumnName = "ProptypeId", insertable = false, updatable = false)
	private PropType propType;
	
}
