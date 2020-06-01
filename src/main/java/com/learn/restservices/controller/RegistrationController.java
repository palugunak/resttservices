package com.learn.restservices.controller;

import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.learn.restservices.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.restservices.entity.Registration;
import com.learn.restservices.helper.DetailsNotFound;
import com.learn.restservices.helper.ResultCodes;
import com.learn.restservices.helper.ResultResponse;
import com.learn.restservices.repository.RegistrationRepository;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationRepository regiRepository;
    @Autowired
    ResultResponse resultResponse;
    @Autowired
    RegistrationService regService;

    @PostMapping("/register")
    public ResultResponse create(@RequestBody Registration regInput) {
        System.out.println("LOGGGGG  ");
        try {
            String saveType = regService.saveUser(regInput);
            if (saveType.equals("1")) {
                resultResponse.setResultCode(ResultCodes.INCORRECT_MSISDN_CODE);
                resultResponse.setResultDescription(ResultCodes.INCORRECT_MSISDN_DESC);
                return resultResponse;
            } else if (saveType.equals("2")) {
                resultResponse.setResultCode(ResultCodes.USER_ALREADY_CODE);
                resultResponse.setResultDescription(ResultCodes.USER_ALREADY_DESC);
                return resultResponse;
            } else if (saveType.equals("INVALID_PASSPORT")) {
                resultResponse.setResultCode(ResultCodes.INVALID_PASSPORT_CODE);
                resultResponse.setResultDescription(ResultCodes.INVALID_PASSPORT_DESC);
                return resultResponse;
            } else if (saveType.equals("INVALID EMAIL")) {
                resultResponse.setResultCode(ResultCodes.INVALID_EMAIL_CODE);
                resultResponse.setResultDescription(ResultCodes.INVALID_EMAIL_DESC);
                return resultResponse;
            } else if (saveType.equals("Duplicate Identification")) {
                resultResponse.setResultCode(ResultCodes.IDENTIFICATION_ALREADY_EXIST_CODE);
                resultResponse.setResultDescription(ResultCodes.IDENTIFICATION_ALREADY_EXIST_DESC);
                return resultResponse;
            } else if (saveType.equals("Duplicate EmailAdresss")) {
                resultResponse.setResultCode(ResultCodes.EmailAddress_ALREADY_EXIST_CODE);
                resultResponse.setResultDescription(ResultCodes.EmailAddress_ALREADY_EXIST_DESC);
                return resultResponse;

            }

            //	 System.out.println(regOutput.getId());
            return new ResultResponse();
        } catch (Exception e) {
            return new ResultResponse();
        }
    }

    @GetMapping("/getDetails/{id}")
    public ResponseEntity<ResultResponse> retrieveStudent(@PathVariable long id) {
        Registration regDetails = regiRepository.findById(id);


        if (regDetails != null) {
            resultResponse.setResultCode(ResultCodes.DETAILS_FOUND_CODE);
            resultResponse.setResultDescription(ResultCodes.DETAILS_FOUND_DESC);
        } else {
            resultResponse.setResultCode(ResultCodes.DETAILS_NOT_FOUND_CODE);
            resultResponse.setResultDescription(ResultCodes.DETAILS_NOT_FOUND_DESC);
        }

        return new ResponseEntity<ResultResponse>(resultResponse, HttpStatus.OK);
    }

    @GetMapping("/getDetailsByName/{name}")
    public ResponseEntity<ResultResponse> retrieveStudent(@PathVariable String name) {
        List<Registration> regDetailsList = regiRepository.findByFirstName(name);


        if (regDetailsList != null && regDetailsList.size() > 0) {
            resultResponse.setResultCode(ResultCodes.DETAILS_FOUND_CODE);
            resultResponse.setResultDescription(ResultCodes.DETAILS_FOUND_DESC);
        } else {
            resultResponse.setResultCode(ResultCodes.DETAILS_NOT_FOUND_CODE);
            resultResponse.setResultDescription(ResultCodes.DETAILS_NOT_FOUND_DESC);
        }

        return new ResponseEntity<ResultResponse>(resultResponse, HttpStatus.OK);
    }

}
