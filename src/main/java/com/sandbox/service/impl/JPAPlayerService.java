package com.sandbox.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.model.Player;
import com.sandbox.repository.PlayerRepository;
import com.sandbox.service.PlayerService;

@Service
public class JPAPlayerService implements PlayerService{

	@Resource
	private PlayerRepository playerRepository;
	
	@Override
	@Transactional
	public Player create(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Player findPlayer(Long accountId) {
		Player player = playerRepository.findByAccountId(accountId);
		
		return player;
	}

}
