package com.example.springsocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsocial.generic.CrudCustom;
import com.example.springsocial.model.SolicitudFirmaElectronica;

@Repository
@Transactional
public interface VerificarFirmaElectronicaRepositorio <T> 
	extends CrudRepository<SolicitudFirmaElectronica, Integer>, 
			PagingAndSortingRepository<SolicitudFirmaElectronica, Integer>, 
			JpaSpecificationExecutor<SolicitudFirmaElectronica>, 
			JpaRepository<SolicitudFirmaElectronica, Integer>, 
			CrudCustom<SolicitudFirmaElectronica> {	
	
	SolicitudFirmaElectronica findById(Long id);
	 List<SolicitudFirmaElectronica> findAll();
	 SolicitudFirmaElectronica existsById(Boolean value);
}
