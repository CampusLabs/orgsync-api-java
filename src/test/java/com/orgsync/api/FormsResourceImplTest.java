package com.orgsync.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Type;

import org.junit.Test;

public class FormsResourceImplTest {

    private final ApiClientImpl client = mock(ApiClientImpl.class);
    private final FormsResourceImpl resource = new FormsResourceImpl(client);

    @Test
    public void testGetFormSubmission() throws Exception {
        resource.getFormSubmission(123);

        verify(client).getResponse(eq(RequestParams.get("/form_submissions/123")), any(Type.class));
    }

}
