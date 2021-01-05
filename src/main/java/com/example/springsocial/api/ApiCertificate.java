package com.example.springsocial.api;

public class ApiCertificate extends Api {

	
	public ApiCertificate() {
		//setApiUrl(System.getenv("CERTIFICATE_API_URL"));
		setApiUrl("http://192.168.79.66:3003");
	}
	
	public void setGetPath(String id, String name, String email, String password) {		setPath("/child/create?"+"id="+id+"&name="+name+"&email="+email+"&password="+password);	}
	//public void setPostPath(String name, String email, String password) {		setPath("/child/create?"+"name="+name+"&"+"email="+email+"&+password="+password);	}
	
}
