package com.example.springsocial.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springsocial.model.Persona;
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

		RestResponse response = new RestResponse();
		try {
	
			List list= repository.listUserData(cui);
			String spliteData[] = list.get(0).toString().split(",");	
			if (spliteData[9]==null) {
				String fullName = spliteData[0]+ spliteData[1];

			}else {
				String fullName = spliteData[0]+ spliteData[1]+ spliteData[9];

			}
			
			String codStatus = spliteData[2];
			String descripcion = spliteData[3];
			String nroCedula1 = spliteData[4];
			String nroCedula2 = spliteData[5];
			String nomDepto = spliteData[6];
			String nomMupio = spliteData[7];
			String cuiDpi =  spliteData[8];
			String nroBoleta =  spliteData[10];
			
			List finalList= repository.validateAfiliation(nroBoleta);

			data.setObject(list);
			response.setData(data);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		
    	return response;
    }
	
}
