package com.orgsync.api;


public abstract class Module<T> {

	public abstract T get(ApiClientImpl client);

}
