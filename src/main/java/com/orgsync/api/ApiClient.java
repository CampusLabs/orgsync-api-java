package com.orgsync.api;

public interface ApiClient {

	public <T> T getModule(Module<T> module);

}
