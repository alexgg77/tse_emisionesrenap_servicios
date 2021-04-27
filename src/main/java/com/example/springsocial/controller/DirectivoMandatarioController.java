package com.example.springsocial.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.repository.DirectivoOMandatarioRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("consultaDirectivoMandatario")
public class DirectivoMandatarioController {
	@Autowired
	private DirectivoOMandatarioRepository repository;	  	
	
	@GetMapping("list/{cui}")
    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String cui) throws Exception {
		ObjectSetGet data= new ObjectSetGet();
		JSONObject jsonResponse = new JSONObject();
		String nroBoleta, splitData[];
		RestResponse response = new RestResponse();
		try {
			List list= repository.listUserData(cui);
			
			if (!list.isEmpty()) {
				splitData = list.get(0).toString().split(",");	
				jsonResponse.put("cui", splitData[0]);
				jsonResponse.put("nroboleta", splitData[1]);
				jsonResponse.put("nombre", splitData[2]);
				jsonResponse.put("status", splitData[3]);
				jsonResponse.put("statusDescripcion", splitData[4]);
				jsonResponse.put("flagAfiliado", splitData[5]);
				jsonResponse.put("cargo", splitData[6]);
		

				

			}else {
				return new RestResponse(null,new CustomException("CUI NO EMPADRONADO O INCORRECTO"
						+ "",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));

			}
			
		
			response.setData(jsonResponse);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		
    	return response;
    }
	
}