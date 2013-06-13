package com.orgsync.api;

import com.orgsync.api.impl.ApiClientImpl;

public abstract class Module<T> {

	public abstract T get(ApiClientImpl client);

}
