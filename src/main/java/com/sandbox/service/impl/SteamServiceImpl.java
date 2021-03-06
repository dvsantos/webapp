package com.sandbox.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sandbox.service.SteamService;
import com.sandbox.service.result.GameMatch;
import com.sandbox.service.result.DotaHero;
import com.sandbox.service.result.DotaItem;
import com.sandbox.service.result.MatchDetailsResult;
import com.sandbox.service.result.MatchHistoryBySequenceNumResult;
import com.sandbox.service.result.MatchHistoryResult;
import com.sandbox.service.result.PlayerInMatch;
import com.sandbox.service.result.PlayerSummary;

@Service(value="simpleService")
public class SteamServiceImpl implements SteamService {

	//https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/V001/?key=81388AF4FDBC32329C1C657A8E11420F&start_at_match_seq_num=283535716
	
	
	private String baseURL = "https://api.steampowered.com/IDOTA2Match_570/";

	private String key = "81388AF4FDBC32329C1C657A8E11420F";
	
	@Override
	public MatchHistoryBySequenceNumResult getMatchHistoryBySequenceNum(long startMatchSeqNum) {
		HttpClient httpclient = new DefaultHttpClient();

		String url = baseURL + "GetMatchHistoryBySequenceNum/V001/" + "?key=" + key
				+ "&start_at_match_seq_num=" + startMatchSeqNum;

		System.out.println(url);
		
		try {
			HttpGet httpget = new HttpGet(url);
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);

			JSONObject obj = new JSONObject(responseBody);
			
			System.out.println("did request");
			
			MatchHistoryBySequenceNumResult result = new MatchHistoryBySequenceNumResult();

			JSONObject res = obj.getJSONObject("result");

			result.setStatus(res.getInt("status"));

			JSONArray matchesArray = res.getJSONArray("matches");

			for (int i = 0; i < matchesArray.length(); i++) {
				System.out.println(i + " " + matchesArray.length());
				
				JSONObject matchObj = matchesArray.getJSONObject(i);

				GameMatch match = new GameMatch();

				match.setMatchId(matchObj.getLong("match_id"));
				match.setMatchSeqNum(matchObj.getLong("match_seq_num"));
				match.setStartTime(matchObj.getLong("start_time"));
				match.setLobbyType(matchObj.getInt("lobby_type"));

				JSONArray playersArray = matchObj.getJSONArray("players");

				for (int j = 0; j < playersArray.length(); j++) {

					JSONObject playerObj = playersArray.getJSONObject(j);

					PlayerInMatch player = new PlayerInMatch();

					try {
						player.setAccountId(playerObj.getLong("account_id"));
					} catch (JSONException e) {
						//TODO bot player?
						e.printStackTrace();
					}
					player.setPlayerSlot(playerObj.getInt("player_slot"));
					player.setHeroId(playerObj.getInt("hero_id"));

					match.getPlayers().add(player);
				}

				result.getMatches().add(match);
			}
			
			System.out.println("ok");
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.sandbox.service.SteamServiceI#getMatchHistory(long)
	 */
	@Override
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

				GameMatch match = new GameMatch();

				match.setMatchId(matchObj.getLong("match_id"));
				match.setMatchSeqNum(matchObj.getLong("match_seq_num"));
				match.setStartTime(matchObj.getLong("start_time"));
				match.setLobbyType(matchObj.getInt("lobby_type"));

				JSONArray playersArray = matchObj.getJSONArray("players");

				for (int j = 0; j < playersArray.length(); j++) {

					JSONObject playerObj = playersArray.getJSONObject(j);

					PlayerInMatch player = new PlayerInMatch();

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
	
	/* (non-Javadoc)
	 * @see com.sandbox.service.SteamServiceI#getMatchDetails(long)
	 */
	@Override
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
			
			List<PlayerInMatch> players = new ArrayList<>();
			
			for (int j = 0; j < playersArray.length(); j++) {
				JSONObject playerObj = playersArray.getJSONObject(j);
				
				PlayerInMatch player = new PlayerInMatch();

				player.setAccountId(playerObj.getLong("account_id"));
				player.setHeroId(playerObj.getInt("hero_id"));
				player.setPlayerSlot(playerObj.getInt("player_slot"));
//				System.out.println(String.format("%o", player.getPlayerSlot()));
//				int b = (player.getPlayerSlot() & 0x80);
//				System.out.println(b == 128);
				player.setKills(playerObj.getInt("kills"));
				player.setDeaths(playerObj.getInt("deaths"));
				player.setAssists(playerObj.getInt("assists"));

				List<DotaItem> items = new ArrayList<>();
				
				DotaItem item0 = new DotaItem();
				item0.setId(playerObj.getInt("item_0"));
				items.add(item0);
				
				DotaItem item1 = new DotaItem();
				item1.setId(playerObj.getInt("item_1"));
				items.add(item1);

				DotaItem item2 = new DotaItem();
				item2.setId(playerObj.getInt("item_2"));
				items.add(item2);
				
				DotaItem item3 = new DotaItem();
				item3.setId(playerObj.getInt("item_3"));
				items.add(item3);
				
				DotaItem item4 = new DotaItem();
				item4.setId(playerObj.getInt("item_4"));
				items.add(item4);
				
				DotaItem item5 = new DotaItem();
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
	
	/* (non-Javadoc)
	 * @see com.sandbox.service.SteamServiceI#getHeroes()
	 */
	@Override
	public List<DotaHero> getHeroes(){
		HttpClient httpclient = new DefaultHttpClient();

		String url = "https://api.steampowered.com/IEconDOTA2_570/" + "GetHeroes/v0001/" + "?key=" + key
				+ "&language=en_us";

		try {
			HttpGet httpget = new HttpGet(url);
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);

			JSONObject obj = new JSONObject(responseBody);
			
			JSONObject res = obj.getJSONObject("result");
			
			JSONArray heroesArray = res.getJSONArray("heroes");

			List<DotaHero> heroes = new ArrayList<>();
			
			for (int j = 0; j < heroesArray.length(); j++) {
				JSONObject heroObj = heroesArray.getJSONObject(j);
				
				DotaHero hero = new DotaHero();
				
				hero.setId(heroObj.getInt("id"));
				hero.setName(heroObj.getString("name"));
				hero.setLocalizedName(heroObj.getString("localized_name"));
				hero.setLanguage("en_us");
				
				heroes.add(hero);
			}
			
			return heroes;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		
		return null;
	}

	//http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=81388AF4FDBC32329C1C657A8E11420F&vanityurl=vernisan
	//76561198063495322
	/* (non-Javadoc)
	 * @see com.sandbox.service.SteamServiceI#getPlayerSummaries(java.util.Set)
	 */
	@Override
	public List<PlayerSummary> getPlayerSummaries(Set<Long> steamids) {
		HttpClient httpclient = new DefaultHttpClient();

		String idsParam  = "";
		
		for(Long steamid: steamids) {
			idsParam += steamid + ",";
		}
		
		String url = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/" + "?key=" + key + "&steamids=" + idsParam  ;
		
		try {
			HttpGet httpget = new HttpGet(url);
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			
			List<PlayerSummary> playerSummaries = new ArrayList<>();

			JSONObject obj = new JSONObject(responseBody);
			
			JSONObject res = obj.getJSONObject("response");
			
			JSONArray playersArray = res.getJSONArray("players");
			
			for (int j = 0; j < playersArray.length(); j++) {
				JSONObject playerObj = playersArray.getJSONObject(j);
				
				PlayerSummary playerSummary = new PlayerSummary();
				playerSummary.setSteamId(playerObj.getLong("steamid"));
				playerSummary.setCommunityVisibilityState(playerObj.getInt("communityvisibilitystate"));
				playerSummary.setPersonaname(playerObj.getString("personaname"));
				playerSummary.setLastLogOff(playerObj.getLong("lastlogoff"));
				playerSummary.setProfileurl(playerObj.getString("profileurl"));
				playerSummary.setAvatar(playerObj.getString("avatar"));
				playerSummary.setAvatarMedium(playerObj.getString("avatarmedium"));
				playerSummary.setAvatarFull(playerObj.getString("avatarfull"));
				playerSummary.setPersonastate(playerObj.getInt("personastate"));
				
				playerSummaries.add(playerSummary);
			}
			
			return playerSummaries;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		SteamService service = new SteamServiceImpl();
//		System.out.println(service.getMatchDetails(305007174));
		
//		System.out.println(service.getHeroes());
		
		
		long a = 83615786l + 76561197960265728l;
		
//		System.out.println(a);
		
	
		Set<Long> ids = new HashSet<>();
//		ids.add(76561198063495322l);
//		ids.add(76561198073973734l);
		
		ids.add(a);
		
//		
//		System.out.println(service.getPlayerSummaries(ids));
		
		System.out.println(service.getMatchHistoryBySequenceNum(283535716l));
		
	}
	
}
