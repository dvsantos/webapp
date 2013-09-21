package com.sandbox.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandbox.service.PlayerService;
import com.sandbox.service.SteamService;
import com.sandbox.service.result.GameMatch;
import com.sandbox.service.result.MatchDetailsResult;
import com.sandbox.service.result.MatchHistoryResult;
import com.sandbox.service.result.PlayerInMatch;
import com.sandbox.view.bean.Hero;
import com.sandbox.view.bean.Item;
import com.sandbox.view.bean.LobbyType;
import com.sandbox.view.bean.PlayerMatch;


@Controller
public class PlayerController {

	@Autowired
	@Qualifier(value="persistedService")
	private SteamService steamService;
	
	@Autowired
	private PlayerService playerService;

	
	@RequestMapping("/testeMerge")
	public String testPlayer() {
		com.sandbox.model.Player player = new com.sandbox.model.Player();
		
		player.setId(1l);
		//player.setAccountId(123456789l);
		player.setPersonaName("teste3");
		
		playerService.create(player);
		
		return "path";
	}
	
	@RequestMapping("/create")
	public String createPlayer() {
		com.sandbox.model.Player player = new com.sandbox.model.Player();
		player.setAccountId(123456789l);
		player.setPersonaName("teste1");
		
		playerService.create(player);
		
		return "path";
	}
	
	// http://localhost:8081/player/103229594/matchHistory
	@RequestMapping("/player/{accountID}/matchHistory")
	public String matchHistory(@PathVariable Long accountID, Model model) {
		 MatchHistoryResult matchHistoryResult = steamService.getMatchHistory(accountID);
		 
		 List<PlayerMatch> matches = new ArrayList<>();
		 
		 for(GameMatch recordedMatch : matchHistoryResult.getMatches().subList(0, 4)) {
			 PlayerMatch match = new PlayerMatch();

			 map(recordedMatch, match, accountID);
			 
			 matches.add(match);
		 }
		 
		 model.addAttribute("playerMatches", matches);
		
		 return "matchHistory";
	}

	private void map(GameMatch recordedMatch, PlayerMatch playerMatch, long playerAccountId) {
		playerMatch.setMatchId(recordedMatch.getMatchId());
		
		Hero hero = new Hero();
		playerMatch.setHero(hero);
		
		for(PlayerInMatch player: recordedMatch.getPlayers()) {
			if(player.getAccountId() == playerAccountId) {
				hero.setId(player.getHeroId());
			}
		}

		MatchDetailsResult matchDetails = steamService.getMatchDetails(recordedMatch.getMatchId());
		
		for(PlayerInMatch p : matchDetails.getPlayers()) {
			if(p.getAccountId() == playerAccountId) {
				playerMatch.setKills(p.getKills());
				playerMatch.setDeaths(p.getDeaths());
				playerMatch.setAssists(p.getAssists());
				
				for(com.sandbox.service.result.DotaItem item: p.getItems()) {
					Item playerItem = new Item();
					playerItem.setId(item.getId());
					playerMatch.getItems().add(playerItem);
				}
				
				int slot = p.getPlayerSlot();

				playerMatch.setWonMatch(false);
				
				if((slot & 0x80) == 128) {
					//was on dire
					if(!matchDetails.isRadiantWin()) {
						playerMatch.setWonMatch(true);
					}
				} else {
					//was on radiant
					if(matchDetails.isRadiantWin()) {
						playerMatch.setWonMatch(true);
					}
				}
				
			}
		}
		
		playerMatch.setDuration(matchDetails.getDuration());
		
		playerMatch.setLobbyType(LobbyType.fromInt(recordedMatch.getLobbyType()));
	}
	
	
}
