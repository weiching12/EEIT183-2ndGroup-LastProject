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
import com.playcentric.model.game.primary.GameTypeLib;
import com.playcentric.service.game.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class GameController {
	
	@Autowired
	private GameService gService;
	
	
	@GetMapping("/back/game")
	public String backGame() {
		return "game/back-game";
	}
	
	
	@GetMapping("/game/getInsertGame")
	public String getInsertGame(Model model) {
		List<GameTypeLib> allType = gService.findAllType();
		model.addAttribute("allType",allType);
		return "game/insert-game";
	}
	
	@PostMapping("/game/insertGame")
	public String insertGame(@ModelAttribute Game game,
			@RequestParam List<Integer> typeId) {
		
		return "redirect:/back/game";
	}
	
	@GetMapping("/game/setDiscount")
	public String setDiscount() {
		return "game/set-discount";
	}
	
	
}
