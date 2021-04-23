package com.example.springsocial.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.api.ApiFiles;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.model.OrganizacionPolitica;
import com.example.springsocial.repository.OrganizacionesPoliticasRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.ApiResponseGetValuesTools;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("organizacionesPoliticas")
public class DatosOrganizacionesPoliticasController {
	@Autowired
	private OrganizacionesPoliticasRepository repository;	  	
	private ApiFiles apiFiles = new ApiFiles();
	private ApiResponseGetValuesTools responseTools = new ApiResponseGetValuesTools();
	private String base64Image;
	
	/*@GetMapping("list/{cui}")
    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String cui) throws Exception {
		ObjectSetGet data= new ObjectSetGet();
		JSONObject jsonResponse = new JSONObject();
		String nroBoleta, splitData[];
		RestResponse response = new RestResponse();
		String base64=null;
		try {
			List list= repository.listUserData(cui);
			
			if (!list.isEmpty()) {
				splitData = list.get(0).toString().split(",");	
				jsonResponse.put("name", splitData[0]);
				jsonResponse.put("idOrganizacion", splitData[1]);
				jsonResponse.put("siglas", splitData[2]);
				jsonResponse.put("departamento", splitData[3]);
				jsonResponse.put("municipio", splitData[4]);
				jsonResponse.put("direccion", splitData[5]);
				jsonResponse.put("telefono", splitData[6]);
				jsonResponse.put("email", splitData[7]);
				jsonResponse.put("representanteLegal", splitData[8]);
				jsonResponse.put("fase", splitData[9]);
				jsonResponse.put("estado", splitData[10]);

				String siglas = splitData[2].trim();
				String id = splitData[1];
				if (id != null || id != "") {
					apiFiles.clearParms();
					apiFiles.setGetPath("partidos_politicos","logos", siglas+".jpg");
					apiFiles.sendGet();
					//jsonResponse.put("base64", apiFiles.getRestResponse());
					responseTools.setApiResponse(apiFiles.getRestResponse());
					responseTools.setValue("base64");
					responseTools.getJsonValue();
					this.base64Image=responseTools.getReturnedValue();
					System.out.println(	this.base64Image);
					jsonResponse.put("base64", this.base64Image);

				}


			}else {
				return new RestResponse(null,new CustomException("CUI NO ASOCIADO A NINGUNA ORGANIZACIÓN POLITICA"
						+ "",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));

			}
			
		
			response.setData(jsonResponse);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		
    	return response;
    }*/
	
	
		@GetMapping("list/{idop}")
	    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal,HttpServletRequest request,	@PathVariable String idop) throws Exception {
			RestResponse response=new RestResponse();
			String splitData[];
			JSONObject jsonResponse = new JSONObject();
			try {								   
			List listado= repository.listUserData(idop);			
			if (!listado.isEmpty()) {
				splitData = listado.get(0).toString().split(",");	
				jsonResponse.put("name", splitData[0]);
				jsonResponse.put("idOrganizacion", splitData[1]);
				jsonResponse.put("siglas", splitData[2]);
				jsonResponse.put("departamento", splitData[3]);
				jsonResponse.put("municipio", splitData[4]);
				jsonResponse.put("direccion", splitData[5]);
				jsonResponse.put("telefono", splitData[6]);
				jsonResponse.put("email", splitData[7]);
				jsonResponse.put("representanteLegal", splitData[8]);
				jsonResponse.put("fase", splitData[9]);
				jsonResponse.put("estado", splitData[10]);

				String siglas = splitData[2].trim();
				String id = splitData[1];
				if (id != null || id != "") {
					apiFiles.clearParms();
					apiFiles.setGetPath("partidos_politicos","logos", siglas+".jpg");
					apiFiles.sendGet();
					//jsonResponse.put("base64", apiFiles.getRestResponse());
					responseTools.setApiResponse(apiFiles.getRestResponse());
					responseTools.setValue("base64");
					responseTools.getJsonValue();
					this.base64Image=responseTools.getReturnedValue();
					System.out.println(	this.base64Image);
					jsonResponse.put("base64", this.base64Image);
			}}else {
				return new RestResponse(null,new CustomException("NO SE ENCONTRO NINGUNA ORGANIZACIÓN POLITICA"
						+ "",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));

			}
			
			response.setData(jsonResponse);
			}catch(Exception ex) {
				CustomException customExcepction=  new CustomException(ex.getMessage(),ex,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
				response.setError(customExcepction);
			}
			return response; 
		}
	}
