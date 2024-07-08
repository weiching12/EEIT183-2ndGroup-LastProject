package com.playcentric.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageLibService {
	
	@Autowired
	private ImageLibRepository imageLibRepo;

	public ImageLib savePhoto(ImageLib imageLib) {
		return imageLibRepo.save(imageLib);
	}
	
	public ImageLib findImageById(Integer imageId) {
		Optional<ImageLib> optional = imageLibRepo.findById(imageId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}
	
	public List<ImageLib> findAllPhotos(){
		return imageLibRepo.findAll();
	}
}
