package com.sandbox.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandbox.model.DotaMatch;
import com.sandbox.model.Faction;
import com.sandbox.model.Hero;
import com.sandbox.model.Item;
import com.sandbox.model.LobbyType;
import com.sandbox.model.Player;
import com.sandbox.model.PlayerMatchResult;
import com.sandbox.model.PlayerSlot;
import com.sandbox.service.MatchService;
import com.sandbox.service.PlayerService;
import com.sandbox.service.SteamService;
import com.sandbox.service.result.DotaItem;
import com.sandbox.service.result.MatchDetailsResult;
import com.sandbox.service.result.PlayerInMatch;

@Controller
public class MatchController {

	@Autowired
	@Qualifier(value="simpleService")
	private SteamService steamService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private PlayerService playerService;
	
	//E a concorrencia?
	@RequestMapping("/match/{matchId}")
	public String showDotaMatch(@PathVariable Long matchId, Model model) {
		
		DotaMatch match = matchService.findMatch(matchId);
		
		if(match != null) {
			System.out.println(match);
		} else {
			MatchDetailsResult matchDetails = steamService.getMatchDetails(matchId);
			
			match = new DotaMatch();
			
			match.setMatchId(matchId);
			match.setMatchSequenceNumber(matchDetails.getMatchSeqNum());
			
	    	long epoch = Long.parseLong( matchDetails.getStartTime().toString() );
	    	Date starTime = new Date( epoch * 1000 );
			match.setStartTime(starTime);
			
			for(PlayerInMatch playerInMatch : matchDetails.getPlayers()){
				PlayerMatchResult playerMatchResult = new PlayerMatchResult();
				
				Player player = playerService.findPlayer(playerInMatch.getAccountId());
				
				if(player == null) {
					player = new Player();
					player.setAccountId(playerInMatch.getAccountId());
					playerService.create(player);
				}
				
				playerMatchResult.setPlayer(player);
				
				playerMatchResult.setKills(playerInMatch.getKills());
				playerMatchResult.setAssists(playerInMatch.getAssists());
				playerMatchResult.setDeaths(playerInMatch.getDeaths());
				
				playerMatchResult.setPlayerSlot(PlayerSlot.fromUnsignedInteger(playerInMatch.getPlayerSlot()));
				
				Hero hero = new Hero();
				hero.setId(playerInMatch.getHeroId());
				
				playerMatchResult.setHero(hero);
				
				for(DotaItem dotaItem : playerInMatch.getItems()) {
					Item item = new Item();
					item.setId(dotaItem.getId());
					
					playerMatchResult.getItems().add(item);
				}
				match.getPlayerMatchResults().add(playerMatchResult);
			}
			
			// e se der pau na partida?
			if(matchDetails.isRadiantWin()) {
				match.setWinningFaction(Faction.RADIANT);
			} else {
				match.setWinningFaction(Faction.DIRE);
			}
			
			match.setLobbyType(LobbyType.fromInt(matchDetails.getLobbyType()));
			
			matchService.create(match);
			
			System.out.println("Match not found");
		}
		
		return "match";
	}
	

}
