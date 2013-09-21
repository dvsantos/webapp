package com.sandbox.service.result;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DotaItem {

	@Id
	private Integer id;

	public Integer getId() {
		if(id == null) return null;
		return id.intValue();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		
		if(id != null && id == 0) {
			//System.out.println("teste");
		}
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(id != null && id == 0) {
//			System.out.println("teste");
		}
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DotaItem))
			return false;
		DotaItem other = (DotaItem) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + "]" + super.toString();
	}
	
	
	
}
