package com.orgsync.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Type;

import org.junit.Test;

public class OrgsModuleImplTest {

	private final ApiClientImpl client = mock(ApiClientImpl.class);
	private final OrgsModuleImpl module = new OrgsModuleImpl(client);

	@Test
	public void testGetOrgs() {
		module.getOrgs();
		verify(client).getResponse(eq(RequestParams.get("/orgs")),
				any(Type.class));
	}

}
