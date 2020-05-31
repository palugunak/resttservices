package com.learn.restservices.helper;

import org.springframework.stereotype.Component;

@Component
public class ResultResponse {

	private String resultCode;
	private String resultDescription;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
