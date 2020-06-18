package org.cyb.controller;

import org.cyb.service.PictureUploadService;
import org.cyb.utils.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureUploadContorller {
	
	@Autowired
	private PictureUploadService pictureUploadService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult pictureUpload(MultipartFile uploadFile) {
		PictureResult pictureUpload = pictureUploadService.pictureUpload(uploadFile);
		
		return pictureUpload;
	}
}
