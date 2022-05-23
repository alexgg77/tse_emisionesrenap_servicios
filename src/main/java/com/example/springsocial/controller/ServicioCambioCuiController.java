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
import com.example.springsocial.model.input.modelCambioCui;
import com.example.springsocial.process.cambioCui.processRegistroCambioCui;
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
@RequestMapping("CambioCui")
public class ServicioCambioCuiController {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EntitiRepository entitiRepository;
	private RestResponse response = new RestResponse();
	private processRegistroCambioCui cambioCui;
	
	
	@ApiOperation(	
            value = "Registro de cambio de cui.",
            notes = "Metodo que recibe un json"
    )
	@ApiResponses(value= {
			@ApiResponse(code=400, message=" Bad request is received"),
			@ApiResponse(code=500, message=" Server Error")
	})
	@PostMapping("create")
	public RestResponse recibirPaquete(@Valid
			@ApiParam(value="Objeto que contiene los datos necesario para registrar los cambios de cui, "
					+ "Si los parametros son REQUERIDOS, ingresarlos para que el metodo funcione correctamente", required=false)
			@RequestBody modelCambioCui element,
			BindingResult bindingresult,
			@CurrentUser UserPrincipal userPrincipal, 
			HttpServletRequest request) throws CustomException{
		try {
			String authTokenHeader = request.getHeader("Authorization");
			
			if(!userPrincipal.hasPermissionToRoute(request)) return new RestResponse(null, new CustomException("Acceso denegado", ErrorCode.ACCESS_DENIED,
					this.getClass().getSimpleName(), 0));
			
			response = new RestResponse();
			cambioCui = new processRegistroCambioCui();
			
			cambioCui.setData(element);
			cambioCui.setEntityManagerFactory(entityManagerFactory);
			cambioCui.iniciarCambioCui();
			
			if (cambioCui.getResponse().getError()!=null)throw new Exception(cambioCui.getResponse().getError().toString());
			else {
				response.setData(cambioCui.getResponse().getData());
			}
			
		}catch (Exception exception) {
			CustomException customExcepction= new CustomException(exception.getMessage(),ErrorCode.REST_UPDATE,this.getClass().getSimpleName(), 0);
			response.setError(customExcepction);
	    }
		
		return response;
	}
	
	
	
}
