package com.mahmud.card.web.controller;

import java.util.List;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.mahmud.card.core.model.Card;
import com.mahmud.card.core.parser.IParser;
import com.mahmud.card.core.service.ICardService;
import com.mahmud.card.web.model.CSVFile;
import com.mahmud.card.web.utils.FileUtils;
import com.mahmud.card.web.validator.FileValidator;


@Controller
@RequestMapping("/fileUpload.htm")
public class FileUploadController {
	
	@Autowired
	FileValidator validator;
	
	@Autowired
	ICardService cardService;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		CSVFile file = new CSVFile();
		model.addAttribute("file", file);
		return "fileUpload";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated @ModelAttribute("file") CSVFile file,
			BindingResult result) {

		String returnVal = "cardDetails";
		if (result.hasErrors()) {
			returnVal = "fileUpload";
		} else {			
			MultipartFile multipartFile = file.getFile();
			File convFile = FileUtils.getFileFromMutlipart(multipartFile);
			IParser parser = cardService.getParser();
			parser.setFile(convFile);
			List<Card> cards = cardService.processCards(true);
	        
	        
			model.addAttribute("cards", cards);
		}
		return returnVal;
	}

}
