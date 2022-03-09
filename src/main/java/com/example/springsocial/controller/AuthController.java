package com.example.springsocial.controller;
import java.io.Serializable;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springsocial.api.ApiLoginSSO;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.model.outputresponse.outLogin;
import com.example.springsocial.payload.LoginRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@SuppressWarnings({"rawtypes", "unchecked","unused"})
@Api(tags="Auth")
@RestController
@RequestMapping("/auth")
public class AuthController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private ApiLoginSSO apiSSO=null;
	RestResponse response;
	
	
	@ApiOperation(
            value = "Auth para servicio fallecidos.",
            response = outLogin.class,
            notes = "Este metodo requiere el ingreso de las credenciales necesarias."
    )
	
	@ApiResponses(value= {
			@ApiResponse(code=400, message=" Bad request is received"),
			@ApiResponse(code=500, message=" Server Error")
	})
	@PostMapping("login")
    public RestResponse login(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request,@Valid @RequestBody LoginRequest loginRequest,BindingResult bindingresult) throws JSONException {
			logger.log(Level.INFO,"Iniciando Login SSO");
			com.alibaba.fastjson.JSONObject  respuesta= new com.alibaba.fastjson.JSONObject();
			response = new RestResponse();
			
			try {
				if(bindingresult.hasErrors()) {
					return new RestResponse(null,new CustomException(bindingresult.getFieldError().getField()+": "+bindingresult.getFieldError().getDefaultMessage(),ErrorCode.REST_CREATE,this.getClass().getSimpleName(),0));
				}
			 String jsonStr=com.alibaba.fastjson.JSONObject.toJSONString(loginRequest);			
			 JSONObject json = new JSONObject(jsonStr);
			 apiSSO=new ApiLoginSSO();
			 apiSSO.clearParms();						
			 apiSSO.setPostPathLogin();
			 apiSSO.setParams(json);
			 apiSSO.sendPost();
			 if (apiSSO.getRestResponse().getError()!=null)throw new Exception(apiSSO.getRestResponse().getError().toString());
				else {													
					respuesta=apiSSO.convertAtJSONTYPE(com.alibaba.fastjson.JSONObject.class);
					response.setData(respuesta);
				}
			}catch(Exception exception) {
				CustomException customExcepction= new CustomException(exception.getMessage(),ErrorCode.REST_UPDATE,this.getClass().getSimpleName(), 0);
				response.setError(customExcepction);
			}
			return response;
	}
}