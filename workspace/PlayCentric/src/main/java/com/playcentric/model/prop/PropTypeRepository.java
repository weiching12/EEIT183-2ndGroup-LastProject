package com.playcentric.model.prop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropTypeRepository extends JpaRepository<PropType, Integer> {
	
}
