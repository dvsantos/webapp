package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.MatchHistoryResult;
import com.sandbox.service.result.MatchHistoryResultKey;

public interface MatchHistoryResultRepository extends
		JpaRepository<MatchHistoryResult, MatchHistoryResultKey> {

}
