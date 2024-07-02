package com.playcentric.model.prop;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playcentric.model.game.transaction.Payment;

public interface PropsRepository extends JpaRepository<Props,Integer> {

}
