package com.mahmud.card.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mahmud.card.web.model.CSVFile;

public class FileValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return CSVFile.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		CSVFile file = (CSVFile) obj;
		  if (file.getFile().getSize() == 0) {
		   errors.rejectValue("file", "valid.empty");
		  }
		  else if (!file.getFile().getOriginalFilename().endsWith("csv")){
			   errors.rejectValue("file", "valid.file");
		  }
	}
}
