package com.orgsync.api.messages;

public class Success {
	private boolean success;

	public final boolean isSuccess() {
		return success;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (success ? 1231 : 1237);
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
		Success other = (Success) obj;
		if (success != other.success)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Success [success=" + success + "]";
	}

}
