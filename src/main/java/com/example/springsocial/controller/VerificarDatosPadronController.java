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
import com.example.springsocial.repository.PadronElectoralRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("verificarDatosPadron")
public class VerificarDatosPadronController {
	@Autowired
	private PadronElectoralRepository repository;	  	
	
	@GetMapping("verificarAfiliacion/{cui}")
    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String cui) throws Exception {
		ObjectSetGet data= new ObjectSetGet();
		JSONObject jsonResponse = new JSONObject();
		String nroBoleta, splitData[];
		RestResponse response = new RestResponse();
		try {
	
			List list= repository.listUserData(cui);
			
			if (list.size()>1) {
				splitData = list.get(0).toString().split(",");	
				jsonResponse.put("codStatus", splitData[0]+splitData[1]);
				jsonResponse.put("codStatus", splitData[2]);
				jsonResponse.put("descripcion", splitData[3]);
				jsonResponse.put("nroCedula1", splitData[4]);
				jsonResponse.put("nroCedula2", splitData[5]);
				jsonResponse.put("nomDepto", splitData[6]);
				jsonResponse.put("nomMupio", splitData[7]);
				jsonResponse.put("cuiDpi", splitData[8]);
				jsonResponse.put("cuiDpi", splitData[10]);
				nroBoleta =  splitData[10];
				
			
				

			}else {
				return new RestResponse(null,new CustomException("El Usuario buscado no esta empadronado o el DPI ingresado es incorrecto, no se pueden obtener los datos"
						+ "",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));

			}
			
			List finalList= repository.validateAfiliation(nroBoleta);
		
			if (finalList.size()<1) {
					jsonResponse.put("afiliacion", "NO AFILIADO");
			}
			else {
			String afiliationSplit[]=finalList.get(0).toString().split(",");
			jsonResponse.put("iDop", afiliationSplit[0]);
			jsonResponse.put("siglas", afiliationSplit[1]);
			jsonResponse.put("nombreOP", afiliationSplit[2]);
			jsonResponse.put("afiliacion", "AFILIADO");
			}
			response.setData(jsonResponse);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		
    	return response;
    }
	
}
