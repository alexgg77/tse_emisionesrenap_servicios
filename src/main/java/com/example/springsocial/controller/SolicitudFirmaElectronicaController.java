package com.example.springsocial.controller;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.api.ApiFiles;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.generic.CrudController;
import com.example.springsocial.model.SolicitudFirmaElectronica;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
import com.example.springsocial.repository.FirmaElectronicaRepositorio;
import com.example.springsocial.repository.SolicitudCertificadoRepositorio;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.FileExtension;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;

@SuppressWarnings({"rawtypes", "unchecked"})
@RestController
@RequestMapping("solicitudFirmaElectronica")
public class SolicitudFirmaElectronicaController<T> implements CrudController {
	@Autowired
	private SolicitudCertificadoRepositorio repository;	    
	@Autowired
	private FirmaElectronicaRepositorio firmarepository;	    
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EntitiRepository entitiRepository;
	private String moduleName="solicitudFirmaElectronica";
	private DateTools dateTools = new DateTools();
	private ApiFiles apiFiles = new ApiFiles();
	private FileExtension  fileExtension= new FileExtension();
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	
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

		RestResponse response = new RestResponse();
		SolicitudFirmaElectronica solicitudCertificado= new SolicitudFirmaElectronica();
		try {
			ObjectSetGet data= new ObjectSetGet();
			data.setObject(createElement);
			boolean pendingRequestExist = repository.existsByesVerificadoAndCreatedBy(false, userPrincipal.getId());
			boolean VerifiedRequestExist = repository.existsByesVerificadoAndCreatedBy(true, userPrincipal.getId());
			boolean firmIsActive = firmarepository.existsByisActiveAndCreatedBy(true, userPrincipal.getId());
			boolean isAprobedWithouSign = repository.existsByesFinalizadoAndEsVerificadoAndEsRechazadoAndCreatedBy(false,true,false, userPrincipal.getId());
			if (data.getValue("requestDocumentFile")==null || data.getValue("requestDocumentFile")=="" ) return new RestResponse(null,new CustomException("Archivo de solicicitud PDF no existe",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			if (data.getValue("signatureFile")==null || data.getValue("signatureFile")=="") return new RestResponse(null,new CustomException("Archivo de firma no existe",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			if (pendingRequestExist == true) return new RestResponse(null,new CustomException("Hay solicitudes pendientes, no se pudo crear su solicitud.",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			if (firmIsActive == true) return new RestResponse(null,new CustomException("Hay una firma electronica activa, no se puede crear su solicitud.",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			if (isAprobedWithouSign == true) return new RestResponse(null,new CustomException("Tiene una solicitud aprobada sin firma, por favor cree la firma.",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));

			solicitudCertificado.setCorreo(userPrincipal.getEmail());
			solicitudCertificado.setCompanyId(userPrincipal.getCompany_id());
			solicitudCertificado.setCreatedBy(userPrincipal.getId());
			solicitudCertificado.setNombre(userPrincipal.getName());
			solicitudCertificado.setCui(userPrincipal.getCode());
			solicitudCertificado.setCreatedAt( dateTools.get_CurrentDate());
			solicitudCertificado.setEsRechazado(false);
			solicitudCertificado.setEsFinalizado(false);
			solicitudCertificado.setEsVerificado(false);
			fileExtension.setBase64(data.getValue("requestDocumentFile").toString());
			fileExtension.setExtension();
			solicitudCertificado.setRutaArchivoSolicitud("solicitud."+fileExtension.getExtension());
			
			fileExtension.setBase64(data.getValue("signatureFile").toString());
			fileExtension.setExtension();
			
			solicitudCertificado.setRutaArchivoFirma("firma."+fileExtension.getExtension());
			repository.save(solicitudCertificado);
			String RequestName = "solicitud";
			String SignatureName = "firma";
			apiFiles.clearParms();
			apiFiles.setPostPath();
			apiFiles.addParam("name",RequestName);
			apiFiles.addParam("base64",data.getValue("requestDocumentFile").toString());
			apiFiles.addParam("type",System.getenv("REQUEST_ROUTE"));
			apiFiles.addParam("folder", solicitudCertificado.getId().toString());
			apiFiles.sendPost();
			RestResponse responseFormulario =apiFiles.getRestResponse();
			System.out.println(responseFormulario);
			if (responseFormulario.getError() !=null) return new RestResponse(null,new CustomException("El archivo de solicitud no se subió correctamente",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			apiFiles.clearParms();
			apiFiles.setPostPath();
			apiFiles.addParam("name",SignatureName);
			apiFiles.addParam("base64",data.getValue("signatureFile").toString());
			apiFiles.addParam("type",System.getenv("REQUEST_ROUTE"));
			apiFiles.addParam("folder",solicitudCertificado.getId().toString());
			apiFiles.sendPost();
			RestResponse responseFirma=apiFiles.getRestResponse();
			System.out.println(responseFirma);
			if (responseFirma.getError() !=null) return new RestResponse(null,new CustomException("El archivo de firma no se ha subido correctamente",ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			

		
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
	
	@GetMapping("openFile/{type}/{folder}/{fileName}")
    @PreAuthorize("hasRole('USER')")
    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String type,@PathVariable String fileName, @PathVariable String folder) throws Exception {
		RestResponse response = new RestResponse();
		 
		apiFiles.clearParms();
		apiFiles.setGetPath(System.getenv("REQUEST_ROUTE"), folder, fileName);
		apiFiles.sendGet();
		response=apiFiles.getRestResponse();
    	return response;
    }
}
