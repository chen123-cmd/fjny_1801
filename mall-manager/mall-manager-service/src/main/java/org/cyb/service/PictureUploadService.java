package org.cyb.service;

import org.cyb.utils.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureUploadService {
	public PictureResult pictureUpload(MultipartFile fileName);
	
}
