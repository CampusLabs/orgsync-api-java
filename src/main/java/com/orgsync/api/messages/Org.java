package com.orgsync.api.messages;

import java.util.Date;

public class Org {

	private int id;
	private String shortName;
	private String longName;
	private Date createdAt;

	public final int getId() {
		return id;
	}

	public final String getShortName() {
		return shortName;
	}

	public final String getLongName() {
		return longName;
	}

	public final Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "Org [id=" + id + ", shortName=" + shortName + ", longName="
				+ longName + ", createdAt=" + createdAt + "]";
	}

}
