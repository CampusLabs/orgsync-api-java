package com.orgsync.api.messages.orgs;

import java.util.List;

public class AddAccounts {

	private final int id;
	private final List<Integer> ids;

	public AddAccounts(final int id, final List<Integer> ids) {
		super();
		this.id = id;
		this.ids = ids;
	}

	public final int getId() {
		return id;
	}

	public final List<Integer> getIds() {
		return ids;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddAccounts other = (AddAccounts) obj;
		if (id != other.id)
			return false;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddAccounts [id=" + id + ", ids=" + ids + "]";
	}

}
