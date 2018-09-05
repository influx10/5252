package com.cts.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cts.vo.BookingSlotVO;

@Component
public class BookingValidator implements Validator {

	private static Map<String, List<BookingSlotVO>> bookingDetails = new HashMap<String, List<BookingSlotVO>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return BookingSlotVO.class.isAssignableFrom(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors errors) {
		// TODO Auto-generated method stub
		BookingSlotVO bookingSlotVO = (BookingSlotVO) arg0;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "required.employeeId",
				"Employee Id is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeName", "required.employeeName",
				"Employee Name is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Email is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required.phone", "Phone No is required!");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName", "required.accountName",
				"Account Name is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assessmentName", "required.assessmentName",
				"Assessment Name is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", "required.level", "Level is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfAssessment", "required.dateOfAssessment",
				"Date of Assessment (dd-MMM-yyyy)! is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slot", "required.slot", "Slot is required!");

		if ((null != bookingSlotVO.getEmployeeId() && !bookingSlotVO.getEmployeeId().isEmpty())) {
			if (!bookingSlotVO.getEmployeeId().matches("\\d{6}")) {
				errors.rejectValue("employeeId", "invalid.employeeId");
			}

		}

		if ((null != bookingSlotVO.getEmployeeName() && !bookingSlotVO.getEmployeeName().isEmpty())) {
			if (!bookingSlotVO.getEmployeeName().matches("^[a-zA-Z\\s]{2,30}$")) {
				errors.rejectValue("employeeName", "invalid.employeeName");
			}
		}

		if ((null != bookingSlotVO.getEmail() && !bookingSlotVO.getEmail().isEmpty())) {
			if (!bookingSlotVO.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
				errors.rejectValue("email", "invalid.email");
			}
		}

		if ((null != bookingSlotVO.getDateOfAssessment())) {
			if (new Date().after((Date) bookingSlotVO.getDateOfAssessment())) {
				errors.rejectValue("dateOfAssessment", "before.dateOfAssessment");
			}
		}

		if (!errors.hasErrors()) {

			if (bookingDetails.get(bookingSlotVO.getEmployeeId()) == null) {
				ArrayList<BookingSlotVO> bookingSlotList = new ArrayList<BookingSlotVO>();
				bookingSlotList.add(bookingSlotVO);
				bookingDetails.put(bookingSlotVO.getEmployeeId(), bookingSlotList);
			} else {

				for (BookingSlotVO bookingSlot : bookingDetails.get(bookingSlotVO.getEmployeeId())) {
					Date date1 = (Date) bookingSlotVO.getDateOfAssessment();
					Date date2 = (Date) bookingSlot.getDateOfAssessment();
					if (bookingSlotVO.getAssessmentName().equals(bookingSlot.getAssessmentName())) {
						errors.rejectValue("assessmentName", "assessmentName.blocked");
						break;
					} else if (date1.compareTo(date2) == 0 && bookingSlotVO.getSlot().equals(bookingSlot.getSlot())) {
						errors.rejectValue("dateOfAssessment", "slot.blocked");
						break;
					} else {
						bookingDetails.get(bookingSlotVO.getEmployeeId()).add(bookingSlotVO);
					}

				}

			}
		}

		for (Map.Entry<String, List<BookingSlotVO>> entry : bookingDetails.entrySet()) {
			entry.getKey();
			entry.getValue();
		}

	}

}