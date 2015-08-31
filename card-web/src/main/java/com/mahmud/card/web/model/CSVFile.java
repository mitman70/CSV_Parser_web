package com.mahmud.card.web.model;

import org.springframework.web.multipart.MultipartFile;

public class CSVFile {
		 
		MultipartFile file;

		public MultipartFile getFile() {
			return file;
		}

		public void setFile(MultipartFile file) {
			this.file = file;
		}
		
}
