package com.sandbox.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandbox.service.SteamService;
import com.sandbox.service.result.Match;
import com.sandbox.service.result.MatchDetailsResult;
import com.sandbox.service.result.MatchHistoryResult;
import com.sandbox.service.result.Player;
import com.sandbox.view.bean.Hero;
import com.sandbox.view.bean.Item;
import com.sandbox.view.bean.LobbyType;
import com.sandbox.view.bean.PlayerMatch;


@Controller
public class PlayerController {

	@Autowired
	private SteamService steamService;
	
	// http://localhost:8081/player/103229594/matchHistory
	
	@RequestMapping("/player/{accountID}/matchHistory")
	public String matchHistory(@PathVariable Long accountID, Model model) {
		 MatchHistoryResult matchHistoryResult = steamService.getMatchHistory(accountID);
		 
		 List<PlayerMatch> matches = new ArrayList<>();
		 
		 for(Match recordedMatch : matchHistoryResult.getMatches().subList(0, 4)) {
			 PlayerMatch match = new PlayerMatch();

			 map(recordedMatch, match, accountID);
			 
			 matches.add(match);
		 }
		 
		 model.addAttribute("playerMatches", matches);
		
		 return "matchHistory";
	}

	private void map(Match recordedMatch, PlayerMatch playerMatch, long playerAccountId) {
		playerMatch.setMatchId(recordedMatch.getMatchId());
		
		Hero hero = new Hero();
		playerMatch.setHero(hero);
		
		for(Player player: recordedMatch.getPlayers()) {
			if(player.getAccountId() == playerAccountId) {
				hero.setId(player.getHeroId());
			}
		}

		MatchDetailsResult matchDetails = steamService.getMatchDetails(recordedMatch.getMatchId());
		
		for(Player p : matchDetails.getPlayers()) {
			if(p.getAccountId() == playerAccountId) {
				playerMatch.setKills(p.getKills());
				playerMatch.setDeaths(p.getDeaths());
				playerMatch.setAssists(p.getAssists());
				
				for(com.sandbox.service.result.Item item: p.getItems()) {
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
