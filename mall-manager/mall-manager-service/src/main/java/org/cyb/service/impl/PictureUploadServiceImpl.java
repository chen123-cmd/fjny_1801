package org.cyb.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.cyb.service.PictureUploadService;
import org.cyb.utils.ExceptionUtil;
import org.cyb.utils.FtpUtil;
import org.cyb.utils.IDUtils;
import org.cyb.utils.PictureResult;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureUploadServiceImpl implements PictureUploadService {

	String host = "127.0.0.1";
	int port = 21;
	String username = "cyb2";
	String password = "123456";

	@Override
	public PictureResult pictureUpload(MultipartFile uploadfile) {

		PictureResult result = new PictureResult();
		try {
			//判断是否为空
			if(null == uploadfile || uploadfile.isEmpty()) {
				result.setError(1);
				result.setMessage("上传图片为空");
				return result;
			}
			//获取文件名
			String originalFilename = uploadfile.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			DateTime dateTime = new DateTime();
			String filePath = dateTime.toString("/yyyy/MM/dd");
			String filename = IDUtils.genImageName() + ext;
			InputStream input = uploadfile.getInputStream();
			FtpUtil.uploadFile(host, port, username, password, "/", filePath, filename, input);
			String url = "http://localhost:8181" + filePath + "/" + filename;
			result.setError(0);
			result.setUrl(url);
			
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			result.setError(1);
			result.setMessage(ExceptionUtil.getStackTrace(e));
		}
		return result;
	}

}
