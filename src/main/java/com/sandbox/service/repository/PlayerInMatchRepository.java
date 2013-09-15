package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.PlayerInMatch;

public interface PlayerInMatchRepository extends
		JpaRepository<PlayerInMatch, Long> {

}
