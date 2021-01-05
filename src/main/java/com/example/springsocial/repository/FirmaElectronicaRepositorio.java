package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsocial.generic.CrudCustom;
import com.example.springsocial.model.FirmaElectronica;
import com.example.springsocial.model.SolicitudFirmaElectronica;

@Repository
@Transactional
public interface FirmaElectronicaRepositorio <T> 
	extends CrudRepository<FirmaElectronica, Integer>, 
			PagingAndSortingRepository<FirmaElectronica, Integer>, 
			JpaSpecificationExecutor<FirmaElectronica>, 
			JpaRepository<FirmaElectronica, Integer>, 
			CrudCustom<FirmaElectronica> {	
	
	FirmaElectronica findById(Long id);
	FirmaElectronica findByisActiveAndCreatedBy(Boolean isActive, Long createdBy);
	boolean existsByisActiveAndCreatedBy(Boolean isActive, Long createdBy);
}
