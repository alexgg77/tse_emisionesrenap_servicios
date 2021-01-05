package com.example.springsocial.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.repository.SolicitudCertificadoRepositorio;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes", "unchecked"})
@RestController
@RequestMapping("padronElectoral")
public class VerificarDatosPadronController {
	@Autowired
	private SolicitudCertificadoRepositorio repository;	  
	
	
	
	@GetMapping("verificarAfiliacion/{dpi}")
    @PreAuthorize("hasRole('USER')")
    public RestResponse openFile(@CurrentUser UserPrincipal userPrincipal, 
    		HttpServletRequest request,
    		@PathVariable String type,@PathVariable String fileName, @PathVariable String folder) throws Exception {
		RestResponse response = new RestResponse();
		
    	return response;
    }
	
}
