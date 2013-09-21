package com.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sandbox.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

	@Query("SELECT p FROM Player p WHERE p.accountId = :accountId")
	public Player findByAccountId(@Param("accountId") Long accountId);
	
}
