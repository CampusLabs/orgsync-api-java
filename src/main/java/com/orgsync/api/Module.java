package com.orgsync.api;

/*package*/abstract class Module<T> {

	/* package */abstract T get(ApiClientImpl client);

}
