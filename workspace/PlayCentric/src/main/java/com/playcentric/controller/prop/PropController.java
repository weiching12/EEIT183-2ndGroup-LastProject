package com.playcentric.controller.prop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.playcentric.model.game.primary.Game;
import com.playcentric.model.prop.Props;
import com.playcentric.model.prop.PropType;
import com.playcentric.service.game.GameService;
import com.playcentric.service.prop.PropService;
import com.playcentric.service.prop.PropTypeService;

@Controller
@SessionAttributes(names = "games")
public class PropController {

    @Autowired
    private PropService propService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PropTypeService propTypeService;

    // 主頁
    @GetMapping("/prop/propSheet")
    public String addHouse() {
        return "prop/propSheet";
    }

    // 找尋所有遊戲回傳至選單
    @GetMapping("/prop/findAllGame")
    @ResponseBody
    public List<Game> findAllGame() {
        return gameService.findAll();
    }

    // 根據遊戲ID找尋所有道具
    @GetMapping("/prop/findAllPropsByGameId")
    @ResponseBody
    public List<Props> findAllPropsByGameId(@RequestParam int gameId) {
        return propService.findPropsByGameId(gameId);
    }

    // 新增道具
    @PostMapping("/prop/saveProp")
    @ResponseBody
    public void save(@RequestBody Props prop) {
        Game game = gameService.findById(prop.getGameId());
        prop.setGame(game);

        PropType propType = propTypeService.findById(prop.getPropTypeId());
        prop.setPropType(propType);

        propService.save(prop);
    }
}
