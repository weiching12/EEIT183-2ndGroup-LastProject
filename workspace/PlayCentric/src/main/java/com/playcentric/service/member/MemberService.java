package com.playcentric.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.playcentric.model.member.GoogleLogin;
import com.playcentric.model.member.GoogleLoginRepository;
import com.playcentric.model.member.Member;
import com.playcentric.model.member.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private GoogleLoginRepository googleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Member addGoogleMem(GoogleLogin memGoogle) {
		Member newMember = new Member();
		String password = "login by google";
		newMember.setGoogeId(memGoogle.getGoogleId());
		newMember.setAccount(memGoogle.getEmail());
		newMember.setPassword(password);
		newMember.setNickname(memGoogle.getName());
		newMember.setMemName(memGoogle.getName());
		newMember.setEmail(memGoogle.getEmail());
		return addMember(newMember);
	}
	
	public Member addMember(Member newMember) {
		if (!newMember.getPassword().contains("login")) {
			String encodedPwd = passwordEncoder.encode(newMember.getPassword());
			newMember.setPassword(encodedPwd);
		}
		newMember.setTotalSpent(0);
		newMember.setRole((short)0);
		newMember.setStatus((short)0);
		newMember.setPoints(0);
		return memberRepository.save(newMember);
	}
	
	public boolean checkAccountExist(String account) {
		return memberRepository.findByAccount(account)!=null;
	}
	
	public boolean checkEmailExist(String email) {
		return memberRepository.findByEmail(email)!=null;
	}
	
	public boolean checkGoogleExist(String googleId) {
		return googleRepository.findById(googleId).isPresent();
	}
	
	public Member checkLogin(String account, String password) {
		Member member = memberRepository.findByAccount(account);
		if (member==null) {
			return member;
		}
		String encodedPassword = member.getPassword();
		return passwordEncoder.matches(password, encodedPassword)? member:null;
	}
}
