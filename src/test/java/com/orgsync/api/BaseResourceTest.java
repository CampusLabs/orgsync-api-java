package com.orgsync.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Type;

public abstract class BaseResourceTest {

    protected final ApiClientImpl client = mock(ApiClientImpl.class);

    public void verifyRequest(final RequestParams params) {
        verify(client).getResponse(eq(params), any(Type.class));
    }

}
