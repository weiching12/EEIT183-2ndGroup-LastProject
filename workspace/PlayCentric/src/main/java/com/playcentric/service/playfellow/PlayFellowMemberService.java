package com.playcentric.service.playfellow;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playcentric.model.member.Member;
import com.playcentric.model.member.MemberRepository;
import com.playcentric.model.playfellow.PlayFellowMember;
import com.playcentric.model.playfellow.PlayFellowMemberRepository;

@Service
public class PlayFellowMemberService {

	@Autowired
	private PlayFellowMemberRepository playFellowMemberRepository;

	// 顯示全部資料
	public List<PlayFellowMember> getAllPlayFellowMembers() {
		return playFellowMemberRepository.findAll();
	}

	// 用id顯示單筆資料
	public PlayFellowMember getPlayFellowMemberById(int id) {
		return playFellowMemberRepository.findById(id).orElse(null);
	}

	// 添加伴遊成員
    public PlayFellowMember addPlayFellowMember(PlayFellowMember playFellowMember) {
        return playFellowMemberRepository.save(playFellowMember);
    }
	
	
}