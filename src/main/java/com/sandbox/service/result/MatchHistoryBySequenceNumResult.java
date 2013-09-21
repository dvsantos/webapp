package com.sandbox.service.result;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MatchHistoryBySequenceNumResult {

	@Id
	private Long matchHistoryBySequenceNumResultId;
	
	private Integer status;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<GameMatch> matches = new ArrayList<>();

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<GameMatch> getMatches() {
		return matches;
	}

	public void setMatches(List<GameMatch> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "MatchHistoryBySequenceNumResult [status=" + status
				+ ", matches=" + matches + "]";
	}
	
}