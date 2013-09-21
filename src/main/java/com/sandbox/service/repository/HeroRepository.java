package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.DotaHero;

public interface HeroRepository extends JpaRepository<DotaHero, Integer> {

}
