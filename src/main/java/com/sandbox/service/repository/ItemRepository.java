package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.DotaItem;

public interface ItemRepository extends JpaRepository<DotaItem, Integer> {

}
