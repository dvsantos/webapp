package com.sandbox.service.result;

import java.util.List;

public class MatchHistoryResult {

	private int status;
	
	//statusDetail
	
	private int numResults;
	
	private int totalResults;
	
	private int resultsRemaining;
	
	private List<Match> matches;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getResultsRemaining() {
		return resultsRemaining;
	}

	public void setResultsRemaining(int resultsRemaining) {
		this.resultsRemaining = resultsRemaining;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
}
