package com.example.springsocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PadronElectoralRepository extends JpaRepository{

	
	@Query
	List<Object[]> listByRolIdNotInRolFormAction(@Param("rol_id") Long rol_id);

}
