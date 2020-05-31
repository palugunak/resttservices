package com.learn.restservices.service;

import com.learn.restservices.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.restservices.entity.Registration;

import java.util.List;

@Component
public class RegistrationService {

	@Autowired
	RegistrationRepository registrationRepository;

	public String saveUser(Registration registration) {

		if(registration.getMsisdnNumber().startsWith("0")) {
			System.out.println("testing ");
			return "1";
		}else if(registration.getMsisdnNumber().toString().length() == 10) {
			System.out.println("testing 1");
			registration.setMsisdnNumber("91" + registration.getMsisdnNumber().toString());
			List<Registration> regList = registrationRepository.findByMsisdnNumber(registration.getMsisdnNumber());
		if(regList.size() > 0){
			return "2";
		}
		}
		System.out.println("testing 2");

		Registration regOutput = registrationRepository.save(registration);
		return "0";
	}
}
