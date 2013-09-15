package com.sandbox.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandbox.service.result.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
