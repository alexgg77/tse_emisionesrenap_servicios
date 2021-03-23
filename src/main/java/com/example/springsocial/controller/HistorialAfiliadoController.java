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
import com.example.springsocial.repository.HistorialAfiliadoRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("consultaHistorialAfiliado")
public class HistorialAfiliadoController {
	@Autowired
	private HistorialAfiliadoRepository repository;	  	
	
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
				jsonResponse.put("organizacionPolitica", splitData[2]);
				jsonResponse.put("siglas", splitData[3]);
				jsonResponse.put("fechaAfiliacion", splitData[4]);
				jsonResponse.put("hoja", splitData[5]);
				jsonResponse.put("linea", splitData[6]);
				jsonResponse.put("fechaOperacion", splitData[7]);
				jsonResponse.put("fechaRecepcion", splitData[8]);
				jsonResponse.put("fechaRenuncia", splitData[9]);
				jsonResponse.put("documentoRenuncia", splitData[10]);
				jsonResponse.put("fechaDeBaja", splitData[11]);
				
				
				
				
		

				

			}else {
				return new RestResponse(null,new CustomException("No tiene historial de renuncias o cui incorrecto"
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