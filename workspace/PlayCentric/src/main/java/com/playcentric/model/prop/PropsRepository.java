package com.playcentric.model.prop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playcentric.model.game.transaction.Payment;

public interface PropsRepository extends JpaRepository<Props, Integer> {

	List<Props> findPropsByGameId(int gameId);

}
