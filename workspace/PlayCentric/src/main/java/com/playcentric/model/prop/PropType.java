package com.playcentric.model.prop;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="propType")
public class PropType {
// PK
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer propTypeId;
    private String propType;
}
