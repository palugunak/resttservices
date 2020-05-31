package com.learn.restservices.helper;

public class SecretQandA {
	
	private String verifyId;
	private String verifyAns;
	
	public String getVerifyId() {
		return verifyId;
	}
	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}
	public String getVerifyAns() {
		return verifyAns;
	}
	public void setVerifyAns(String verifyAns) {
		this.verifyAns = verifyAns;
	}
	@Override
	public String toString() {
		return "{verifyId:" + verifyId + ", verifyAns:" + verifyAns + "}";
	}
	
}
