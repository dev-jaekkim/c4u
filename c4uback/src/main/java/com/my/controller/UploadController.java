package com.my.controller;

import java.io.File;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@CrossOrigin("*")
@RestController
@Log4j
public class UploadController {
	@PostMapping("/lesson/upload")
	public void uploadFile(MultipartFile[] uploadFile) {
		String uploadFolder = "C:\\uploadFolder";

		for (MultipartFile multipartFile : uploadFile) {
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			String uploadFileName =  multipartFile.getOriginalFilename();
			File saveFile = new File(uploadFolder, uploadFileName);
			try { 
				multipartFile.transferTo(saveFile);
				
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		}
	}

}
