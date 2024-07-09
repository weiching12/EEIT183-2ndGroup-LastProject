package com.playcentric.controller.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.playcentric.model.game.primary.Game;
import com.playcentric.model.game.primary.GameDiscountSet;
import com.playcentric.model.game.primary.GameTypeLib;
import com.playcentric.service.game.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class GameController {
	
	@Autowired
	private GameService gService;
	
	//遊戲管理後台
	@GetMapping("/back/game")
	public String backGame() {
		return "game/back-game";
	}
	
	//輸入新增遊戲資料
	@GetMapping("/game/getInsertGame")
	public String getInsertGame(Model model) {
		List<GameTypeLib> allType = gService.findAllType();
		List<GameDiscountSet> allDiscount = gService.findAllDiscountSets();
		model.addAttribute("allType",allType);
		model.addAttribute("allDiscount",allDiscount);
		return "game/insert-game";
	}
	
	//進行新增遊戲
	@PostMapping("/game/insertGame")
	public String insertGame(@ModelAttribute Game game,
			@RequestParam List<Integer> typeId) {
		
		return "redirect:/back/game";
	}
	
	//輸入優惠活動資料
	@GetMapping("/game/getSetDiscount")
	public String getSetDiscount() {
		return "game/set-discount";
	}
	
	//新增優惠活動
	@PostMapping("/game/setDiscount")
	public String setDiscount(@ModelAttribute GameDiscountSet discountSet) {
		gService.addDiscountSet(discountSet);
		return "redirect:/back/game";
	}
	
	
}
