package com.abc.adminservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.adminservice.entity.Admin;

public interface AdminDao extends JpaRepository<Admin,Integer>  {
	

}
