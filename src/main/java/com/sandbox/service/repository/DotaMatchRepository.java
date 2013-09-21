package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.GameMatch;

public interface DotaMatchRepository extends JpaRepository<GameMatch, Long> {

}
