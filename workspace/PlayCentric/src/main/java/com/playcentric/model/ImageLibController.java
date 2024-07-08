package com.playcentric.model;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageLibController {
	
	@Autowired
	private ImageLibService imageLibService;
	
	@GetMapping("/image/upload")
	public String upload() {
		return "image/uploadPage";
	}
	
	@PostMapping("/image/uploadPost")
	public String uploadPost(@RequestParam MultipartFile file, Model model) throws IOException{
		
		ImageLib imageLib = new ImageLib();
		imageLib.setImageFile(file.getBytes());
		
		imageLibService.savePhoto(imageLib);
		
		model.addAttribute("okMsg", "上傳成功");
		
		return "image/uploadPage";
		
	}

	@GetMapping("/image/download")
	public ResponseEntity<byte[]> downloadPhotos(@RequestParam Integer imageId) {
		
		ImageLib photos = imageLibService.findImageById(imageId);
		
		byte[] photoFile = photos.getImageFile();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
				                         // body,    headers,  http status code
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
	}

}
