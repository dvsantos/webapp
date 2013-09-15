package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.DotaMatch;

public interface MatchRepository extends JpaRepository<DotaMatch, Long> {

}
