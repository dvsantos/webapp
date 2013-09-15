package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.PlayerSummary;

public interface PlayerSummaryRepository extends
		JpaRepository<PlayerSummary, Long> {

}
