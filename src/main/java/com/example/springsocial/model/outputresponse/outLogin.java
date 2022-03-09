package com.example.springsocial.model.outputresponse;

import io.swagger.annotations.ApiModelProperty;

public class outLogin {
	
	@ApiModelProperty(notes="Token de acceso")
	private String accessToken;
	@ApiModelProperty(notes="Tipo de token")
    private String tokenType;
    
    
	public outLogin() {
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
    
    
}
