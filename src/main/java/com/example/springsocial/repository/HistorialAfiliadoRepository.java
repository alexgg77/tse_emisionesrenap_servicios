package com.example.springsocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsocial.model.OrganizacionesPoliticas;

@Repository
@Transactional
public interface HistorialAfiliadoRepository extends JpaRepository<OrganizacionesPoliticas, String> 
{   

	@Query(value = " SELECT"
	+"	dpi.cui as CUI,"
	+"  h.nroboleta as NROBOLETA,"
    +"	o.nombreop as ORGANIZACIONPOLITICA,"
    +"	o.siglas as SIGLAS,"
    +" 	h.FECAFILIACION AS FECHAAFILIACION,"
    +"	h.HOJA as HOJA,"
    +"	h.LINEA as LINEA,"
    +"	h.FECCRE as FECHAOPERACION,"
    +"	h.FECREC as FECHARECEPCION,"
    +"	h.FECRENUNCIA as FECHARENUNCIA,"
    +"	h.DOCRENUNCIA as DOCUMENTORENUNCIA,"
    +"	h.FECDEL as FECHADEBAJA"
    +"	from"
    +"	tafiliadoshist h, tdpi dpi, torgpoliticas o"
    +"	where"
    +"	h.nroboleta= dpi.nroboleta"
    +"	and h.idop= o.idop"
    +"	and dpi.cui= :cui",nativeQuery = true)
	
	List<String> listUserData(@Param("cui") String cui);


	}
