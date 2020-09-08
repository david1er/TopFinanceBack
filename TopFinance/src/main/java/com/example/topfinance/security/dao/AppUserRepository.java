package com.example.topfinance.security.dao;

import com.example.topfinance.entities.Compte_ope_cour;
import com.example.topfinance.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findUserByUsername(String username);

	@Query(value = "select * from app_user a,app_role r, app_user_roles s where a.id=s.app_user_id and s.roles_id=r.id", nativeQuery = true)
	public List<AppUser> findUsers();
}
