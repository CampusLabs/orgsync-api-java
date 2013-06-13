package com.orgsync.api;

public interface ApiClient {

	public static final String HOST = "https://api.orgsync.com/api/";

	public static final Version DEFAULT_VERSION = Version.V2;

	public <T> T getModule(Module<T> module);

}
