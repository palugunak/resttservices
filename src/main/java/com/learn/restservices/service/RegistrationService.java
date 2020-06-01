package com.learn.restservices.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.learn.restservices.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.restservices.entity.Registration;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    public String saveUser(Registration registration) {

        boolean iden = identificationCheck(registration);
        System.out.println("testing iden" + iden);
        boolean idenEmail = validateEmaladdress(registration.getEmail());
        System.out.println("testing email" + idenEmail);
        if (registration.getMsisdnNumber().startsWith("0")) {
            System.out.println("testing ");
            return "1";
        } else if (iden == false) {
            return "INVALID_PASSPORT";
        } else if (idenEmail == false) {
            return "INVALID EMAIL";
        } else if (registration.getMsisdnNumber().length() > 10 || registration.getMsisdnNumber().length() < 10) {

            if (registration.getMsisdnNumber().length() == 12) {
                List<Registration> regList = registrationRepository.findByMsisdnNumber(registration.getMsisdnNumber());
                if (regList.size() > 0) {
                    return "2";
                }
            } else if (registration.getMsisdnNumber().length() == 10) {

                registration.setMsisdnNumber("91" + registration.getMsisdnNumber().toString());
                List<Registration> regList = registrationRepository.findByMsisdnNumber(registration.getMsisdnNumber());
                if (regList.size() > 0) {
                    return "2";
                }
            } else {
                return "1";
            }

        } else if (idenEmail) {
            System.out.println("iden::" + iden);
            System.out.println("testing email ");
            List<Registration> regList = registrationRepository.findByEmail(registration.getEmail());
            if (regList.size() > 0) {
                System.out.println("testing email1 ");
                return "Duplicate EmailAdresss";
            }
        } else if (iden) {
            System.out.println("testing identification number ");
            List<Registration> regList = registrationRepository.findByIdentificationNumber(registration.getIdentificationNumber());
            if (regList.size() > 0) {
                System.out.println("testing identification number 1 ");
                return "Duplicate Identification";
            }
        } else {
            return "2";
        }System.out.println("testing 2");
        Registration regOutput = registrationRepository.save(registration);
        return "0";
    }

    public boolean identificationCheck(Registration registration) {

        String regex = "^[A-PR-WYa-pr-wy][1-9]\\d"
                + "\\s?\\d{4}[1-9]$";
        String str = registration.getIdentificationNumber();
        Pattern p = Pattern.compile(regex);

        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();

    }

    public boolean validateEmaladdress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}