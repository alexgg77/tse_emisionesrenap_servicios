package com.example.springsocial.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
import com.example.springsocial.repository.HistorialAfiliacionRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("consultaHistorialAfiliado")
public class HistorialAfiliadoController {
	@Autowired
	private HistorialAfiliacionRepository repository;	  	

	
	
	
	@GetMapping("list/{cui}")
    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String cui) throws Exception {
		ObjectSetGet data= new ObjectSetGet();
		JSONObject jsonResponse = new JSONObject();
		String nroBoleta, splitData[];
		RestResponse response = new RestResponse();
		try {
			boolean existCui = repository.existsByCui(cui);
			if (!existCui) return new RestResponse(null,new CustomException("El cui ingresado no existe en los registros.",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			List list = repository.findAllByCui(cui);
			
		
			response.setData(list);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		
    	return response;
    }
	
}