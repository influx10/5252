/**
 *
 */
package com.cts.employeeregistration.controller;


import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.bookmyseat.exception.BookMySeatException;
import com.cognizant.bookmyseat.login.vo.UserDetailsVO;
import com.cts.employeeregistration.fileuploader.FileUploader;
import com.cts.validator.BookingValidator;

/**
 * This is the controller class which gets inputs from jsp and send back the
 * response to jsp
 *
 */
@Controller
public class EmployeeRegistrationController {

	@Autowired
	BookingValidator bookingValidator;
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	HttpSession httpSession;

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

	
	@RequestMapping(method=RequestMethod.GET, value="gotoLogin")
	public ModelAndView goToLogin(Locale locale) {
		
		ModelAndView modelView = new ModelAndView();
		if((null != httpSession.getAttribute("sessionTimeOut")) && Boolean.valueOf(String.valueOf((httpSession.getAttribute("sessionTimeOut"))))) {
			modelView.getModelMap().addAttribute("loginError",messageSource.getMessage("login.timeout", new Object[] {}, locale));
		}
		modelView.getModelMap().addAttribute("user",new UserDetailsVO());
		modelView.setViewName("login");
		
		
		
		return modelView;
	}

	// Add appropriate annotations and code wherever required

	@RequestMapping(method=RequestMethod.POST, value="submitLogin")
	public String submitLogin(@ModelAttribute("user") UserDetailsVO user,@CookieValue(value="name", required=false) Locale locale, Model model){
	
		if((user.getUsername() !=null && !user.getUsername().isEmpty()) && (user.getPassword() !=null && !user.getPassword().isEmpty()) && user.getUsername().equals(user.getPassword()) ) {
			return"home";
		}else {
			model.addAttribute("loginError", messageSource.getMessage("login.invalid",new Object[] {},locale));
			return "login";
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws BookMySeatException {
		
		session.setMaxInactiveInterval(10);
		if (session.isNew()) {
			System.out.println("Logging out, due to inactive session");
			try {
				session.setAttribute("sessionTimeOut", true);
				response.sendRedirect("gotoLogin");

				return false;
			} catch (IOException e) {
				throw new BookMySeatException("IOException is thrown");
			}
		}

		System.out.println("Exiting post handle preHandle..." + request.getRequestURI());
		return true;
	}
}
