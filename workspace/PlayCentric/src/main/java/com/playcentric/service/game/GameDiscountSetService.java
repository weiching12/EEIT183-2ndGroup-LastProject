package com.playcentric.service.game;

import java.util.List;

import com.playcentric.model.game.primary.GameDiscountSet;
import com.playcentric.model.game.primary.GameDiscountSetRepository;

public class GameDiscountSetService {

	private GameDiscountSetRepository rep;
	
	//新增優惠活動
	public GameDiscountSet insert(GameDiscountSet discountSet) {
		return rep.save(discountSet);
	}
	
	//獲取所有優惠活動
	public List<GameDiscountSet> findAll() {
		return rep.findAll();
	}
	
}
