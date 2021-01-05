package com.example.springsocial.controller;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.api.ApiCertificate;
import com.example.springsocial.api.ApiFiles;
import com.example.springsocial.api.ApiSignDocument;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.generic.CrudController;
import com.example.springsocial.model.FirmaElectronica;
import com.example.springsocial.model.SolicitudFirmaElectronica;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
import com.example.springsocial.repository.FirmaElectronicaRepositorio;
import com.example.springsocial.repository.SolicitudCertificadoRepositorio;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;
import com.nimbusds.jose.util.Base64;

@SuppressWarnings({"rawtypes", "unchecked"})
@RestController
@RequestMapping("misFirmas")
public class FirmaElectronicaController<T> implements CrudController {
	@Autowired
	private FirmaElectronicaRepositorio repository;	   
	@Autowired
	private SolicitudCertificadoRepositorio solicitudRepository;	
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EntitiRepository entitiRepository;
	private String moduleName="firmaElectronica";
	private DateTools dateTools = new DateTools();
	@Autowired
    private PasswordEncoder passwordEncoder;
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ApiCertificate apiCertificate = new ApiCertificate();
	private ApiFiles apiFiles = new ApiFiles();
	private ApiSignDocument apiSignDocument= new ApiSignDocument();


	@PostConstruct
	private void init() {
		crud.setRepository(this.repository);
		crud.setModelName(this.moduleName);
		crud.setEntitiRepository(this.entitiRepository);
		crud.setElementRepository(this.elementRepository);
		repositories.put(this.moduleName,this.repository);
	}	 
	
	@Override
	@PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public  RestResponse create(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,@RequestBody Object  createElement) {
		crud.setModelName(getModelNameFromPath(request));

		init();
		if (!userPrincipal.hasPermissionToRoute(request))
			return new RestResponse(null,new CustomException("Acceso denegado",ErrorCode.ACCESS_DENIED, this.getClass().getSimpleName(),0));
		
		RestResponse response = new RestResponse();
		FirmaElectronica firmaElectronica= new FirmaElectronica();

		try {
			ObjectSetGet data= new ObjectSetGet();
			data.setObject(createElement);
			if (data.getValue("solicitud_id")==null || data.getValue("solicitud_id")=="" ) return new RestResponse(null,new CustomException("El id no puede estar vacío",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			if (data.getValue("password")==null || data.getValue("password")=="" ) return new RestResponse(null,new CustomException("La contraseña está vacía",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			boolean firmIsActive = repository.existsByisActiveAndCreatedBy(true, userPrincipal.getId());
			if (firmIsActive == true) return new RestResponse(null,new CustomException("Hay una firma electronica activa, para crear una nueva debe cancelar la que está activa y solicitar una nueva",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			String password = passwordEncoder.encode(data.getValue("password").toString());
			String email = userPrincipal.getEmail().toString();
			Base64 base64Password = Base64.encode(data.getValue("password").toString());
			Base64 base64Email = Base64.encode(userPrincipal.getEmail().toString());
			Base64 base64Name = Base64.encode(userPrincipal.getName().toString());

			firmaElectronica.setCreatedBy(userPrincipal.getId());
			firmaElectronica.setIsActive(true);
			firmaElectronica.setCreatedAt( dateTools.get_CurrentDate());
			firmaElectronica.setClave(password);
			firmaElectronica.setCompanyId(userPrincipal.getCompany_id());
			Long solicitud_id = Long.parseLong(data.getValue("solicitud_id").toString());
			firmaElectronica.setSolicitud_firma_electronica_id(solicitud_id);
			repository.save(firmaElectronica);
			SolicitudFirmaElectronica solicitudCertificado= solicitudRepository.findById(solicitud_id);
			solicitudCertificado.setEsFinalizado(true);
			solicitudCertificado.setFechaFinalizado(dateTools.get_CurrentDate());
			repository.save(solicitudCertificado);	
			Long id  = firmaElectronica.getId();
			apiCertificate.clearParms();
			apiCertificate.setGetPath(id.toString(), base64Name.toString(), base64Email.toString(), base64Password.toString());
			apiCertificate.sendGet();
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		
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
		searchCriteriaTools.addIgualAnd("createdBy",userPrincipal.getId().toString());// filtrar las solicitudes por el usuario actual
    	crud.list(searchCriteriaTools.getSearchCriteria(), orderCriteria);
    	return crud.getResponse();
    }
	
	
	@GetMapping("listAll")
    @PreAuthorize("hasRole('USER')")
    public RestResponse listAll(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request, @RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
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
	
	@PatchMapping("rechazarFirma/{id}/{observacion}")
    @PreAuthorize("hasRole('USER')")
    public RestResponse rechazar(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request,@PathVariable Long id, @PathVariable String observacion) {
		init();
		if (!userPrincipal.hasPermissionToRoute(request))
			return new RestResponse(null,new CustomException("Acceso denegado",ErrorCode.ACCESS_DENIED, this.getClass().getSimpleName(),0));
		
		RestResponse response = new RestResponse();
		try {
			
			FirmaElectronica firmaElectronica = repository.findById(id);
			firmaElectronica.setIsActive(false);
			firmaElectronica.setObservacion(observacion);
			firmaElectronica.setFechaCancelado(dateTools.get_CurrentDate());
			repository.save(firmaElectronica);	
			response.setData(firmaElectronica);
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(customExcepction);
		}
		return response;
	}
	
	
	
	@PostMapping("validarCredencialesFirma")
    @PreAuthorize("hasRole('USER')")
	   public RestResponse validarCredencialesFirma(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request, @RequestBody Object  createElement) {
			init();
			if (!userPrincipal.hasPermissionToRoute(request))
				return new RestResponse(null,new CustomException("Acceso denegado",ErrorCode.ACCESS_DENIED, this.getClass().getSimpleName(),0));
			
			RestResponse response = new RestResponse();
			JSONObject jsonResponse = new JSONObject();
			try {
				
				ObjectSetGet data= new ObjectSetGet();
				data.setObject(createElement);
				if (data.getValue("certificatePassword")==null || data.getValue("certificatePassword")=="" ) return new RestResponse(null,new CustomException("Contraseña Vacía",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));				
				String contraseña = (data.getValue("certificatePassword").toString());
				FirmaElectronica firmaElectronica = repository.findByisActiveAndCreatedBy(true, userPrincipal.getId());
				boolean PasswordValidation = passwordEncoder.matches(contraseña.toString(), firmaElectronica.getClave());
				if (PasswordValidation==false) return new RestResponse(null,new CustomException("CONTRASEÑA INCORRECTA",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));				

				String firmaId = firmaElectronica.getId().toString();
				String solicitudId = firmaElectronica.getSolicitud_firma_electronica_id().toString();
			
				jsonResponse.put("firmaElectronicaID", firmaId);
				jsonResponse.put("solicitudFirmaElectronicaID", solicitudId);

		        response.setData(jsonResponse);
		
			
			}catch(Exception exception) {
				CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
				response.setError(customExcepction);
			}
			return response;
		}
	
	
	
}
