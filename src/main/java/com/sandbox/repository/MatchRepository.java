package com.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.model.DotaMatch;

public interface MatchRepository extends JpaRepository<DotaMatch, Long>{

}
