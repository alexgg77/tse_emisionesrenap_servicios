package com.example.springsocial.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.springsocial.generic.CrudCustom;
import com.example.springsocial.model.twsEncabezado;

@Repository
@Transactional
public interface twsEncabezadoRepository  extends 
CrudRepository<twsEncabezado, Long>,
JpaRepository<twsEncabezado, Long>,
PagingAndSortingRepository<twsEncabezado, Long>,
JpaSpecificationExecutor<twsEncabezado>,
CrudCustom<twsEncabezado>
{
	@Query(value="SELECT * FROM tws_encabezadofalle WHERE estadoprocesado = 0 AND ROWNUM <=1",nativeQuery=true)
	twsEncabezado obtenerEncabezado();
}
