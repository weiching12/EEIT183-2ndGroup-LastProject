package com.playcentric.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.playcentric.model.member.Member;
import com.playcentric.service.member.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/regist")
	public String registPage() {
		return "member/registMember";
	}
	
	@PostMapping("/member/regist")
	@ResponseBody
	public String registMember(@ModelAttribute Member member) {
		try {
			if (hasInfo(member)) {
				memberService.addMember(member);
				return "註冊成功!";
			}
			return "請填妥資料";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "註冊失敗";
	}
	
	private boolean hasInfo(Member member) {
		return member.getAccount()!=null && 
				member.getEmail()!=null &&
				member.getPassword()!=null &&
				member.getNickname()!=null &&
				member.getMemName()!=null;
	}
}
