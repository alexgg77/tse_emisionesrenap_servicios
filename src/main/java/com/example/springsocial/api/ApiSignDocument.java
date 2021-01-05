package com.example.springsocial.api;

public class ApiSignDocument extends Api {

	
	public ApiSignDocument() {
		//setApiUrl(System.getenv("CERTIFICATE_SIGN_URL"));
		setApiUrl("http://192.168.79.66:5000");
	}
	
	public void setGetPath(String id, String name, String email, String password) {		setPath("/child/create?"+"id="+id+"&name="+name+"&email="+email+"&password="+password);	}
	public void setPostPath() {		setPath("/sign/createBase64");	}
	
}
