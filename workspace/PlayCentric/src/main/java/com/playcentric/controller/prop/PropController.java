package com.playcentric.controller.prop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.playcentric.model.game.primary.Game;
import com.playcentric.service.game.GameService;
import com.playcentric.service.prop.PropService;
import com.playcentric.model.prop.Props;

import ch.qos.logback.core.model.Model;

@Controller
@SessionAttributes(names = "games")
public class PropController {

	@Autowired
	private PropService propService;

	@Autowired
	private GameService gameService;
	
//	主頁
	@GetMapping("/prop/propSheet")
	public String addHouse() {
		return "prop/propSheet";
	}
	
	

// 找尋所有遊戲回傳至選單
	@GetMapping("/prop/findAllGame")
	@ResponseBody
	public List<Game> findAllGame() {
		List<Game> games = gameService.findAll();
        return games;

	}
	
// 根據遊戲ID找尋所有道具
//	@GetMapping("/prop/findAllPropsByGameId")
//	@ResponseBody
//	public 

//}
	}
