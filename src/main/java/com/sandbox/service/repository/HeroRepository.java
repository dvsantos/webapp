package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.Hero;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
