package com.example.springsocial.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.crud.CRUD;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.generic.CrudController;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
import com.example.springsocial.repository.HistorialAfiliacionRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("consultaHistorialAfiliado")
public class HistorialAfiliadoController <T> implements CrudController{
	@Autowired
	private HistorialAfiliacionRepository repository;	  	
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EntitiRepository entitiRepository;
	private String moduleName="HistoricoAfiliacionModel";
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private DateTools dateTools =  new DateTools();
	private CRUD crud = new CRUD();
	@PostConstruct
	private void init() {
		crud.setRepository(this.repository);
		crud.setModelName(this.moduleName);
		crud.setEntitiRepository(this.entitiRepository);
		crud.setElementRepository(this.elementRepository);
		repositories.put(this.moduleName,this.repository);
	}	
	
	
	@GetMapping("list/{cui}")
    public RestResponse consultarHistorial(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String cui) throws Exception {
		
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
