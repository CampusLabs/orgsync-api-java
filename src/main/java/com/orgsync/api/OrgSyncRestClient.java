package com.orgsync.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.orgsync.api.messages.ApiError;
import com.orgsync.api.messages.Org;

public class OrgSyncRestClient {

    private static final String HOST = "https://api.orgsync.com/api/";

    private static final Version DEFAULT_VERSION = Version.V2;

    private final String apiKey;

    private final Version version;

    private final AsyncHttpClient client;

    private final Gson gson;

    public OrgSyncRestClient(final String apiKey) {
        this(apiKey, DEFAULT_VERSION);
    }

    public OrgSyncRestClient(final String apiKey, final Version version) {
        this.apiKey = apiKey;
        this.version = version;
        this.client = new AsyncHttpClient();
        this.gson = createGson();
    }

    public void destroy() {
        getClient().close();
    }

    public ApiResponse<List<Org>> getOrganizations() {
        return getResponse(makeCall("/orgs"), new TypeToken<List<Org>>() {
        }.getType());
    }

    private <T> ApiResponse<T> getResponse(final Response result,
            final Type type) {
        String body = null;
        try {
            body = result.getResponseBody();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (result.getStatusCode() == 200 && body != null) {
            return new ApiResponse<T>(gson.fromJson(body, type), null);
        } else {
            return new ApiResponse<T>(null, gson.fromJson(body, ApiError.class));
        }
    }

    private Response makeCall(final String endpoint) {

        try {
            String url = toURL(endpoint);
            System.out.println("Sending request to: " + url);
            return getClient().prepareGet(url).execute().get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private AsyncHttpClient getClient() {
        return client;
    }

    private Gson createGson() {
        return new Gson();
    }

    private String toURL(final String endpoint) {
        return new StringBuilder().append(HOST).append(version.getPath())
                .append(endpoint).append("?key=").append(apiKey).toString();
    }

    public static void main(final String[] args) {
        OrgSyncRestClient client = new OrgSyncRestClient(
                "dd6b9d2beb614611c5eb9f56c34b743d1d86f385");

        ApiResponse<List<Org>> result = client.getOrganizations();

        if (result.isSuccess()) {
            System.out.println(joinList(result.getResult(), "\n"));
        } else {
            System.out.println(result.getError());
        }

        client.destroy();
    }

    private static <T> String joinList(final List<T> list, final String sep) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            str.append(list.get(i).toString());
            if (i < list.size() - 1) {
                str.append(sep);
            }
        }

        return str.toString();
    }
}
