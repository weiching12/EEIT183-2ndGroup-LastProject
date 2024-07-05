package com.playcentric.controller.playfellow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.playcentric.model.ImageLib;
import com.playcentric.model.member.Member;
import com.playcentric.model.member.MemberRepository;
import com.playcentric.model.playfellow.ImageLibPfmemberAssociation;
import com.playcentric.model.playfellow.ImageLibPfmemberAssociationRepository;
import com.playcentric.model.playfellow.ImageLibRepository;
import com.playcentric.model.playfellow.PlayFellowMember;
import com.playcentric.model.playfellow.PlayFellowMemberRepository;
import com.playcentric.service.playfellow.PlayFellowMemberService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayFellowMemberController {

	@Autowired
	PlayFellowMemberService playFellowMemberService;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PlayFellowMemberRepository playFellowMemberRepository;
	@Autowired
	private ImageLibRepository imageLibRepository;
	@Autowired
	private ImageLibPfmemberAssociationRepository imageLibPfmemberAssociationRepository;

	@GetMapping("/playFellow/member")
	public String getAllPlayFellowMembers(Model model) {
		List<PlayFellowMember> playFellowMembers = playFellowMemberService.getAllPlayFellowMembers();
		model.addAttribute("PlayFellowMember", playFellowMembers);
		return "playFellow/PlayFellowMembers";
	}

	@GetMapping("/playFellow/memId/{memId}") // 從memId下去查
	public String getMemberByIdAndAddPFmem(@PathVariable int memId, Model model) {
		Optional<Member> optMember = memberRepository.findById(memId);// 找會員id 性別和生日存到model
		System.out.println(memId);
		if (optMember.isPresent()) {
			Member member = optMember.get();
			model.addAttribute("members", member);

			return "playFellow/addPlayFellowMember";

		} else {
			model.addAttribute("錯誤", "查無會員或未登入");
			return "member/registMember";
		}
	}

	// add伴遊者資料
	@PostMapping("/playFellow/add") // 按下新增
	public String AddPlayFellowMember(@ModelAttribute("members") Member member,
			@ModelAttribute("playFellowMember") PlayFellowMember playFellowMember) {

		playFellowMember.setMember(member);

		Member existingMember = memberRepository.findById(member.getMemId()).orElse(null);
		existingMember.setGender(member.getGender());
		existingMember.setBirthday(member.getBirthday());
		memberRepository.save(existingMember); // 更新 Member 的 gender 和 birthday

		playFellowMemberService.addPlayFellowMember(playFellowMember);

		return "redirect:/playFellow/member"; // 重定向到伴遊成員列表頁
	}

	// 檢查暱稱是否重複
	@GetMapping("/playFellow/checkNickname")
	public ResponseEntity<String> checkNickname(@RequestParam String pfnickname) {
		boolean isDuplicate = playFellowMemberRepository.existsByPfnickname(pfnickname);
		if (isDuplicate) {
			return ResponseEntity.ok("伴遊暱稱重複");
		} else {
			return ResponseEntity.ok("伴遊暱稱沒重複");
		}

	}

	@ResponseBody
	@GetMapping("/playFellow/add/Images") // 要存照片entity+pfmember的照片編號
	public String addImageFile(@RequestParam("file") MultipartFile[] file,
			@RequestParam("playFellowId") Integer playFellowId) throws IOException {

		// 找到對應的playFellowId
		PlayFellowMember playFellowMember = playFellowMemberService.getPlayFellowMemberById(playFellowId);

		List<ImageLibPfmemberAssociation> associations = new ArrayList<>();

		for (MultipartFile oneFile : file) {
			ImageLib imageLib = new ImageLib();
			imageLib.setImageFile(oneFile.getBytes());

			ImageLib saveImage = imageLibRepository.save(imageLib);

			ImageLibPfmemberAssociation ipfa = new ImageLibPfmemberAssociation(playFellowMember, saveImage);

			associations.add(ipfa);
		}
		imageLibPfmemberAssociationRepository.saveAll(associations);

		return "上傳歐給";
	}

}
