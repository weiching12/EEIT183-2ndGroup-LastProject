package com.playcentric.model.game.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCartsRepository extends JpaRepository<GameCarts,GameAndMemId> {

}
