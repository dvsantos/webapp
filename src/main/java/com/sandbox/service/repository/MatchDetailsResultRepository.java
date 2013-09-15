package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.MatchDetailsResult;

public interface MatchDetailsResultRepository extends
		JpaRepository<MatchDetailsResult, Long> {

}
