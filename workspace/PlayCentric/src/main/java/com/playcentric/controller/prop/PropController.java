package com.playcentric.controller.prop;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.playcentric.model.ImageLib;
import com.playcentric.model.game.primary.Game;
import com.playcentric.model.prop.Props;
import com.playcentric.model.prop.PropType;
import com.playcentric.service.game.GameService;
import com.playcentric.service.prop.PropService;
import com.playcentric.service.prop.PropTypeService;
import com.playcentric.service.ImageLibService;

@Controller
@SessionAttributes(names = "games")
public class PropController {

	@Autowired
	private PropService propService;

	@Autowired
	private GameService gameService;

	@Autowired
	private PropTypeService propTypeService;

	@Autowired
	private ImageLibService imageLibService; // 确保已经注入 ImageLibService

	// 主页
	@GetMapping("/prop/propSheet")
	public String addHouse() {
		return "prop/propSheet";
	}

	// 找寻所有游戏返回至选单
	@GetMapping("/prop/findAllGame")
	@ResponseBody
	public List<Game> findAllGame() {
		return gameService.findAll();
	}

	// 根据游戏ID找寻所有道具
	@GetMapping("/prop/findAllPropsByGameId")
	@ResponseBody
	public List<Props> findAllPropsByGameId(@RequestParam int gameId) {
		return propService.findPropsByGameId(gameId);
	}

	// 根据游戏ID找寻所有道具类型
	@GetMapping("/prop/findAllPropTypesByGameId")
	@ResponseBody
	public List<PropType> findAllPropTypesByGameId(@RequestParam int gameId) {
		return propTypeService.findAllPropTypesByGameId(gameId);
	}

	// 新增道具
	@PostMapping("/prop/saveProp")
	@ResponseBody
	public String save(@RequestParam("propName") String propName, @RequestParam("propRarity") String propRarity,
			@RequestParam("propDescription") String propDescription, @RequestParam("propTypeId") Integer propTypeId,
			@RequestParam("gameId") Integer gameId, @RequestParam("propImage") MultipartFile propImage)
			throws IOException {

//        System.out.println("Received gameId: " + gameId);
//        System.out.println("Received propName: " + propName);
//        System.out.println("Received propRarity: " + propRarity);
//        System.out.println("Received propDescription: " + propDescription);
//        System.out.println("Received propTypeId: " + propTypeId);

		Props prop = new Props();
		prop.setGameId(gameId);
		prop.setPropName(propName);
		prop.setPropRarity(propRarity);
		prop.setPropDescription(propDescription);
		prop.setPropTypeId(propTypeId);
		Game game = gameService.findById(gameId);
		if (game == null) {
			throw new IllegalArgumentException("Invalid gameId: " + gameId);
		}
		System.out.println("Found game: " + game.getGameName());
		prop.setGame(game);

		PropType propType = propTypeService.findById(propTypeId);
		if (propType == null) {
			throw new IllegalArgumentException("Invalid propTypeId: " + propTypeId);
		}
		System.out.println("Found propType: " + propType.getPropType());
		prop.setPropType(propType);

		ImageLib imageLib = new ImageLib();
		imageLib.setImageFile(propImage.getBytes());

		// 保存图片
		imageLibService.saveImage(imageLib);
		System.out.println("Saved image with ID: " + imageLib.getImageId());

		// 关联图片
		prop.setPropImageId(imageLib.getImageId());

		// 设置创建时间
		prop.setCreatedTime(LocalDateTime.now());

		System.out.println(prop.getGameId() + "跨加");
		// 保存道具
		propService.save(prop);
		System.out.println("Saved prop with ID: " + prop.getPropId());

		return "上傳OK!!";

	}

//  刪除道具
    @DeleteMapping("/prop/deleteProp")
    public ResponseEntity<String> deleteProp(@RequestParam Integer id) {
        propService.deleteById(id);
        return ResponseEntity.ok("道具已成功删除");
    }
}