package com.mahmud.card.web.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	public static File getFileFromMutlipart(MultipartFile multipartFile) {
		File convFile = new File( multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(convFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convFile;
	}

}
