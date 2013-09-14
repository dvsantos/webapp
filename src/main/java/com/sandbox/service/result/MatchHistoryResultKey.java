package com.sandbox.service.result;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Embeddable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MatchHistoryResultKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4118793192593451198L;

	private Long accountId;
	
	private Long startAtMatchId;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getStartAtMatchId() {
		return startAtMatchId;
	}

	public void setStartAtMatchId(Long startAtMatchId) {
		this.startAtMatchId = startAtMatchId;
	}
	
	@Override
	public String toString() {
		return "MatchHistoryResultKey [accountId=" + accountId
				+ ", startAtMatchId=" + startAtMatchId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result
				+ ((startAtMatchId == null) ? 0 : startAtMatchId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchHistoryResultKey other = (MatchHistoryResultKey) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (startAtMatchId == null) {
			if (other.startAtMatchId != null)
				return false;
		} else if (!startAtMatchId.equals(other.startAtMatchId))
			return false;
		return true;
	}
	
}
