package com.sandbox.service;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sandbox.service.result.Match;
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

}
