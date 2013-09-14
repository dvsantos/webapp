package com.sandbox.service.result;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MatchHistoryResult {

	@EmbeddedId
	private MatchHistoryResultKey matchHistoryResultKey;
	
	private Integer status;

	// statusDetail

	private Integer numResults;

	private Integer totalResults;

	private Integer resultsRemaining;

	@ManyToMany
	private List<Match> matches = new ArrayList<>();

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNumResults() {
		return numResults;
	}

	public void setNumResults(Integer numResults) {
		this.numResults = numResults;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getResultsRemaining() {
		return resultsRemaining;
	}

	public void setResultsRemaining(Integer resultsRemaining) {
		this.resultsRemaining = resultsRemaining;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "MatchHistoryResult [status=" + status + ", numResults="
				+ numResults + ", totalResults=" + totalResults
				+ ", resultsRemaining=" + resultsRemaining + ", matches="
				+ matches + "]";
	}

}
