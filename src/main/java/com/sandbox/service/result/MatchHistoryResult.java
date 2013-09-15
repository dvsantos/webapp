package com.sandbox.service.result;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DotaMatch> matches = new ArrayList<>();
	
	public MatchHistoryResultKey getMatchHistoryResultKey() {
		return matchHistoryResultKey;
	}

	public void setMatchHistoryResultKey(MatchHistoryResultKey matchHistoryResultKey) {
		this.matchHistoryResultKey = matchHistoryResultKey;
	}

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

	public List<DotaMatch> getMatches() {
		return matches;
	}

	public void setMatches(List<DotaMatch> matches) {
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
