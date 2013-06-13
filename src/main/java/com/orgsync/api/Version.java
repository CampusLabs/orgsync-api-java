package com.orgsync.api;

public enum Version {

	V2("v2");

	private final String path;

	private Version(final String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
