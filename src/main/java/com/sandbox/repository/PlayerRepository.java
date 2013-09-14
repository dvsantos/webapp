package com.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sandbox.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

}
