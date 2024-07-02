package com.playcentric.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playcentric.config.GoogleOAuth2Config;
import com.playcentric.service.member.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes(names = {"loginMember"})
public class GoogleOAuth2Controller {
    
	
	@Autowired
	private GoogleOAuth2Config googleOAuth2Config;
	
	@Autowired
	private MemberService memberService;
	
	private final String scope = "https://www.googleapis.com/auth/userinfo.eamil";

	@GetMapping("/google-login")
	public String googleLogin() {
		String authUrl = "https://accounts.google.com/o/oauth2/v2/auth?"
				+ "client_id=" + googleOAuth2Config.getClientId()
				+ "&response_type=code"
				+ "&redirect_uri=" + googleOAuth2Config.getRedirectUri()
				+ "&state=state"
				+ "&scope=openid%20email%20profile";
		return "redirect:" + authUrl;
	}
	
	@GetMapping("/google-callback")
	public String googleCallback(@RequestParam(required = false) String code, Model model) {
		if (code == null) {
			String authUrl = googleLogin();
			authUrl = authUrl.substring(0, authUrl.lastIndexOf("openid%20email%20profile"))
					+ scope;
			return authUrl;
		}
		RestClient restClient = RestClient.create();
		try {
			//設定request params
			String requestBody = UriComponentsBuilder.newInstance()
			.queryParam("code", code)
			.queryParam("client_id", googleOAuth2Config.getClientId())
			.queryParam("client_secret", googleOAuth2Config.getClientSecret())
			.queryParam("redirect_uri", googleOAuth2Config.getRedirectUri())
			.queryParam("grant_type", "authorization_code")
			.build()
			.getQuery();
			//向google發送request取得token
			String credentials = restClient.post()
			.uri("https://oauth2.googleapis.com/token")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(requestBody)
			.retrieve()
			.body(String.class);
			//取得token字串
			JsonNode jsonNode = new ObjectMapper().readTree(credentials);
			String accessToken = jsonNode.get("access_token").asText();
			String idToken = jsonNode.get("id_token").asText();
			//利用token再次向google發送請求，找user
			String payloadResponse = restClient.get()
			.uri("https://www.googleapis.com/oauth2/v1/userinfo?"
					+ "alt=json&access_token="+accessToken)
			.header("Authorization", "Bearer"+idToken)
			.retrieve().body(String.class);
			//取得該帳號email
			JsonNode userInfo = new ObjectMapper().readTree(payloadResponse);
			String userEmail = userInfo.get("email").asText();
			
			boolean hasUser = memberService.checkEmailExist(userEmail);
			//檢查是否已經有此帳號
			Users user = null; 
			if (hasUser) {
				user = memberService.findByName(userEmail);
			}else {
				user = memberService.addGoogleUsers(userEmail);
			}
			
			model.addAttribute("loginUserId", user.getId());
			model.addAttribute("loginUsername", user.getUsername());
			model.addAttribute("loginOK", user.getUsername() + "登入成功");
			return "redirect:/";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "users/loginPage";
		}
//		return "redirect:/";
	}
}
