package com.orgsync.api;

public interface ApiClient {

    public void destroy();

    public <T> T getModule(Module<T> module);

}
