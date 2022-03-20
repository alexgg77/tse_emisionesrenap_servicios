package com.example.springsocial.controller;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.process.ProcesoReporteRenap;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
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
@RequestMapping("recepcionFallecidos")
public class ServicioRecepcionFallecidoController {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EntitiRepository entitiRepository;
	
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private RestResponse response = new RestResponse();
	private ObjectSetGet data= new ObjectSetGet();
	
	
	@ApiOperation(	
            value = "Consulta de fallecidos.",
            notes = "Metodo devuelve que recibe un json"
    )
	@ApiResponses(value= {
			@ApiResponse(code=400, message=" Bad request is received"),
			@ApiResponse(code=500, message=" Server Error")
	})
	@PostMapping("recibirPaquete")
	public RestResponse recibirPaquete(@Valid
			@ApiParam(value="Objeto que contiene los datos necesario para obtener la informacion de cada registro, "
					+ "Si los parametros son REQUERIDOS, ingresarlos para que el metodo funcione correctamente", required=false)
			@RequestBody ReporteRenap element,
			BindingResult bindingresult,
			@CurrentUser UserPrincipal userPrincipal, 
			HttpServletRequest request) throws CustomException{
		String authTokenHeader = request.getHeader("Authorization");
		
		//if(!userPrincipal.hasPermissionToRoute(request)) return new RestResponse(null, new CustomException("Acceso denegado", ErrorCode.ACCESS_DENIED,
		//		this.getClass().getSimpleName(), 0));
		
		response = new RestResponse();
		try {
			data.setObject(element);
			if(bindingresult.hasErrors()) {
				return new RestResponse(null,new CustomException(bindingresult.getFieldError().getField()+": "+bindingresult.getFieldError().getDefaultMessage(),ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
			}
			
				ProcesoReporteRenap procesar = new ProcesoReporteRenap();	
				procesar.setData(element);
				procesar.setEntityManagerFactory(entityManagerFactory);
				procesar.setToken(authTokenHeader);
				procesar.setUserPrincipal(userPrincipal);
				procesar.procesar();
			
			if (procesar.getResponse().getError()!=null)throw new Exception(procesar.getResponse().getError().toString());
			else {
				response.setData(procesar.getResponse().getData());
			}
			
		}catch (Exception exception) {
			CustomException customExcepction= new CustomException(exception.getMessage(),ErrorCode.REST_UPDATE,this.getClass().getSimpleName(), 0);
			response.setError(customExcepction);
	    }
		
		return response;
	}
	
	
	
}
