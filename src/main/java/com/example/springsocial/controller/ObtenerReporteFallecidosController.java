package com.example.springsocial.controller;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.model.twsDetalle;
import com.example.springsocial.model.twsEncabezado;
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.model.outputresponse.CustomResponseReporte;
import com.example.springsocial.process.ProcesoReporteRenap;
import com.example.springsocial.process.ProcesoUpdateEstadoReporte;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
import com.example.springsocial.repository.twsDetalleRepository;
import com.example.springsocial.repository.twsEncabezadoRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@SuppressWarnings({"rawtypes","unchecked","unused"})
@RestController
@RequestMapping("obtenerReporte")
public class ObtenerReporteFallecidosController {
	
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EntitiRepository entitiRepository;
	@Autowired
	private twsEncabezadoRepository encabezadoReposiotry;
	@Autowired 
	private twsDetalleRepository detalleRepository;
	private twsEncabezado encabezadoModel;
	private List<twsDetalle> detalleModel;
	private CustomResponseReporte respuestaCustom;
	private ProcesoUpdateEstadoReporte update;
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private RestResponse response = new RestResponse();
	private ObjectSetGet data= new ObjectSetGet();
	
	@GetMapping("listadoReporte")
	public RestResponse list(@CurrentUser UserPrincipal userPrincipal,HttpServletRequest request) {
		response = new RestResponse();	
		
		try{
			encabezadoModel = new twsEncabezado();
			respuestaCustom = new CustomResponseReporte();
			encabezadoModel = encabezadoReposiotry.obtenerEncabezado();
			detalleModel = detalleRepository.listadoFallecidos(encabezadoModel.getId().toString());
			
			respuestaCustom.setEncabezadoModel(encabezadoModel);
			respuestaCustom.setListadoFallecidos(detalleModel);
			
			response.setData(respuestaCustom);
		}catch(Exception exception) {
			CustomException customExcepction= new CustomException(exception.getMessage(),ErrorCode.REST_UPDATE,this.getClass().getSimpleName(), 0);
			response.setError(customExcepction);
		}
		
		return response;
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("modificar")
	public RestResponse modificar(
			@RequestBody Object element,
			@CurrentUser UserPrincipal userPrincipal, 
			HttpServletRequest request
			) throws CustomException {
		String authTokenHeader = request.getHeader("Authorization");
		data= new ObjectSetGet();
		response = new RestResponse();
		update = new ProcesoUpdateEstadoReporte();
		try {
			update.setData(element);
			update.setEntityManagerFactory(entityManagerFactory);
			update.iniciarUpdate();
			
		}catch (Exception exception) {
			CustomException customExcepction= new CustomException(exception.getMessage(),ErrorCode.REST_UPDATE,this.getClass().getSimpleName(), 0);
			response.setError(customExcepction);
	    }
		
		return response;
	}
	
}
