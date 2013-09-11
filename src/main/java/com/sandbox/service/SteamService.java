package com.sandbox.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sandbox.service.result.Item;
import com.sandbox.service.result.Match;
import com.sandbox.service.result.MatchDetailsResult;
import com.sandbox.service.result.MatchHistoryResult;
import com.sandbox.service.result.Player;

@Service
public class SteamService {

	private String baseURL = "https://api.steampowered.com/IDOTA2Match_570/";

	private String key = "81388AF4FDBC32329C1C657A8E11420F";

	public MatchHistoryResult getMatchHistory(long accountID) {

		HttpClient httpclient = new DefaultHttpClient();

		String url = baseURL + "GetMatchHistory/V001/" + "?key=" + key
				+ "&account_id=" + accountID;

		try {
			HttpGet httpget = new HttpGet(url);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);

			JSONObject obj = new JSONObject(responseBody);

			MatchHistoryResult result = new MatchHistoryResult();

			JSONObject res = obj.getJSONObject("result");

			result.setStatus(res.getInt("status"));
			result.setNumResults(res.getInt("num_results"));
			result.setTotalResults(res.getInt("total_results"));
			result.setResultsRemaining(res.getInt("results_remaining"));

			JSONArray matchesArray = res.getJSONArray("matches");

			for (int i = 0; i < matchesArray.length(); i++) {

				JSONObject matchObj = matchesArray.getJSONObject(i);

				Match match = new Match();

				match.setMatchId(matchObj.getLong("match_id"));
				match.setMatchSeqNum(matchObj.getLong("match_seq_num"));
				match.setStartTime(matchObj.getLong("start_time"));
				match.setLobbyType(matchObj.getInt("lobby_type"));

				JSONArray playersArray = matchObj.getJSONArray("players");

				for (int j = 0; j < playersArray.length(); j++) {

					JSONObject playerObj = playersArray.getJSONObject(j);

					Player player = new Player();

					player.setAccountId(playerObj.getLong("account_id"));
					player.setPlayerSlot(playerObj.getInt("player_slot"));
					player.setHeroId(playerObj.getInt("hero_id"));

					match.getPlayers().add(player);
				}

				result.getMatches().add(match);
			}
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return null;
	}
	
	public MatchDetailsResult getMatchDetails(long matchId) {
		
		HttpClient httpclient = new DefaultHttpClient();

		String url = baseURL + "GetMatchDetails/V001/" + "?key=" + key
				+ "&match_id=" + matchId;

		try {
			HttpGet httpget = new HttpGet(url);
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);

			JSONObject obj = new JSONObject(responseBody);
			
			MatchDetailsResult matchDetailsResult = new MatchDetailsResult();
			matchDetailsResult.setMatchId(matchId);
			
			JSONObject res = obj.getJSONObject("result");
			
			JSONArray playersArray = res.getJSONArray("players");
			
			List<Player> players = new ArrayList<>();
			
			for (int j = 0; j < playersArray.length(); j++) {
				JSONObject playerObj = playersArray.getJSONObject(j);
				
				Player player = new Player();

				player.setAccountId(playerObj.getLong("account_id"));
				player.setHeroId(playerObj.getInt("hero_id"));
				player.setPlayerSlot(playerObj.getInt("player_slot"));
//				System.out.println(String.format("%o", player.getPlayerSlot()));
//				int b = (player.getPlayerSlot() & 0x80);
//				System.out.println(b == 128);
				player.setKills(playerObj.getInt("kills"));
				player.setDeaths(playerObj.getInt("deaths"));
				player.setAssists(playerObj.getInt("assists"));

				List<Item> items = new ArrayList<>();
				
				Item item0 = new Item();
				item0.setId(playerObj.getInt("item_0"));
				items.add(item0);
				
				Item item1 = new Item();
				item1.setId(playerObj.getInt("item_1"));
				items.add(item1);

				Item item2 = new Item();
				item2.setId(playerObj.getInt("item_2"));
				items.add(item2);
				
				Item item3 = new Item();
				item3.setId(playerObj.getInt("item_3"));
				items.add(item3);
				
				Item item4 = new Item();
				item4.setId(playerObj.getInt("item_4"));
				items.add(item4);
				
				Item item5 = new Item();
				item5.setId(playerObj.getInt("item_5"));
				items.add(item5);
				
				player.setItems(items);
				
				players.add(player);
			}
			
			matchDetailsResult.setPlayers(players);
			
			matchDetailsResult.setRadiantWin(res.getBoolean("radiant_win"));
			matchDetailsResult.setDuration(res.getInt("duration"));
			matchDetailsResult.setStartTime(res.getLong("start_time"));
			matchDetailsResult.setMatchSeqNum(res.getLong("match_seq_num"));
			matchDetailsResult.setFirstBloodTime(res.getInt("first_blood_time"));
			matchDetailsResult.setLobbyType(res.getInt("lobby_type"));
			matchDetailsResult.setHumanPlayers(res.getInt("human_players"));
			matchDetailsResult.setLeagueId(res.getInt("leagueid"));
			matchDetailsResult.setGameMode(res.getInt("game_mode"));
		
			return matchDetailsResult;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		
		return null;
	}

	public static void main(String[] args) {
		
		SteamService service = new SteamService();
		System.out.println(service.getMatchDetails(305007174));
		
	}
	
}
