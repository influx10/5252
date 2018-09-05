/**
 *
 */
package com.cts.employeeregistration.controller;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cts.employeeregistration.fileuploader.FileUploader;

/**
 * This is the controller class which gets inputs from jsp and send back the
 * response to jsp
 *
 */
@Controller
public class EmployeeRegistrationController {



	/**
	 * @param model
	 * @return String This method will load the values from jsp
	 */
	@RequestMapping(value = "/fileupload", method = RequestMethod.GET)
	public ModelAndView onLoadSubscribe(Model model) {
		FileUploader filemodel = new FileUploader();
		ModelAndView modelAndView = new ModelAndView("fileupload", "file",
				filemodel);
		return modelAndView;
	}

	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String fileUpload(@Validated FileUploader filemodel,
			BindingResult result, ModelMap model) throws IOException {
		if (result.hasErrors()) {

			return "error";
		} else {

			MultipartFile multipartFile = filemodel.getFile();
				
			model.addAttribute("FileName", multipartFile.getOriginalFilename());
		
			if (multipartFile.isEmpty()) {
				return "error";
			}

			return "success";
		}
	}

}
