package com.example.springsocial.controller;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.api.ApiFiles;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.generic.CrudController;
import com.example.springsocial.model.SolicitudFirmaElectronica;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
import com.example.springsocial.repository.VerificarFirmaElectronicaRepositorio;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;

@SuppressWarnings({"rawtypes", "unchecked"})
@RestController
@RequestMapping("verificarSolicitudFirmaElectronica")
public class VerificarSolicitudFirmaElectronicaController<T> implements CrudController {
	@Autowired
	private VerificarFirmaElectronicaRepositorio repository;	    
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EntitiRepository entitiRepository;
	private String moduleName="solicitudFirmaElectronica";
	private DateTools dateTools = new DateTools();
	private ApiFiles apiFiles = new ApiFiles();
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();

	@PostConstruct
	private void init() {
		crud.setRepository(this.repository);
		crud.setModelName(this.moduleName);
		crud.setEntitiRepository(this.entitiRepository);
		crud.setElementRepository(this.elementRepository);
		repositories.put(this.moduleName,this.repository);
	}	 
	
	@PatchMapping("autorizar/{id}/{fechaExpiracion}")
    @PreAuthorize("hasRole('USER')")
    public RestResponse autorizar(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request,@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy")  Date fechaExpiracion) {
		init();
		if (!userPrincipal.hasPermissionToRoute(request))
			return new RestResponse(null,new CustomException("Acceso denegado",ErrorCode.ACCESS_DENIED, this.getClass().getSimpleName(),0));
		
		RestResponse response = new RestResponse();
		try {
			SolicitudFirmaElectronica solicitudCertificado= repository.findById(id);
			solicitudCertificado.setEsVerificado(true);
			solicitudCertificado.setFechaExpiracion(fechaExpiracion);
			solicitudCertificado.setFechaVerificado( dateTools.get_CurrentDate());
			repository.save(solicitudCertificado);	
			response.setData(solicitudCertificado);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		return response;
	}
	
	@PatchMapping("rechazar/{id}/{observacion}")
    @PreAuthorize("hasRole('USER')")
    public RestResponse rechazar(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request,@PathVariable Long id, @PathVariable String observacion) {
		init();
		if (!userPrincipal.hasPermissionToRoute(request))
			return new RestResponse(null,new CustomException("Acceso denegado",ErrorCode.ACCESS_DENIED, this.getClass().getSimpleName(),0));
		
		RestResponse response = new RestResponse();
		try {
			SolicitudFirmaElectronica solicitudCertificado= repository.findById(id);
			solicitudCertificado.setEsRechazado(true);
			solicitudCertificado.setObservacion(observacion);
			solicitudCertificado.setEsVerificado(true);
			solicitudCertificado.setFechaVerificado( dateTools.get_CurrentDate());

			repository.save(solicitudCertificado);	
			response.setData(solicitudCertificado);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		return response;
	}
	
	@GetMapping("openFile/{type}/{folder}/{fileName}")
    @PreAuthorize("hasRole('USER')")
    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String type,@PathVariable String fileName, @PathVariable String folder) throws Exception {
		
		init();
		if (!userPrincipal.hasPermissionToRoute(request))
			return new RestResponse(null,new CustomException("Acceso denegado",ErrorCode.ACCESS_DENIED, this.getClass().getSimpleName(),0));
		
		RestResponse response = new RestResponse();
		 
		apiFiles.clearParms();
		apiFiles.setGetPath(type, folder, fileName);
		apiFiles.sendGet();
		response=apiFiles.getRestResponse();
    	return response;
    }
	
	@Override
	@GetMapping("list")
    @PreAuthorize("hasRole('USER')")
    public RestResponse list(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request, @RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
		init();
		if (!userPrincipal.hasPermissionToRoute(request))
			return new RestResponse(null,new CustomException("Acceso denegado",ErrorCode.ACCESS_DENIED, this.getClass().getSimpleName(),0));
		
		if (userPrincipal.getCode().toString().length()==0) 
			return new RestResponse(null,new CustomException("CUI incorrecto",ErrorCode.REST_VALIDATIONS, this.getClass().getSimpleName(),0));
		
		crud.setRequest(request);
		crud.setUserPrincipal(userPrincipal);
		searchCriteriaTools.clear();
		searchCriteriaTools.addIgualAnd("createdAt",userPrincipal.getId().toString());// filtrar las solicitudes por el usuario actual
    	crud.list(searchCriteriaTools.getSearchCriteria(), orderCriteria);
    	return crud.getResponse();
    }
}
