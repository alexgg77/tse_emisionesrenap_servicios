package com.example.springsocial.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.springsocial.generic.CrudCustom;
import com.example.springsocial.model.twsDetalle;


@Repository
@Transactional
public interface twsDetalleRepository extends 
CrudRepository<twsDetalle, Long>,
JpaRepository<twsDetalle, Long>,
PagingAndSortingRepository<twsDetalle, Long>,
JpaSpecificationExecutor<twsDetalle>,
CrudCustom<twsDetalle>{
	
	@Query(value="select * from tws_detallefalle where idencabezadofalle = :id",nativeQuery=true)
	List<twsDetalle> listadoFallecidos(@Param("id") String id);
}
