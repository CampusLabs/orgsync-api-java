package com.orgsync.api;

/*package*/abstract class Resource<T> {

    /* package */abstract T get(ApiClientImpl client);

}
