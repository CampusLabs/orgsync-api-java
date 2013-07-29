# Obtaining the Client

All access to the API are made through the [ApiClient][api_client] interface.  To acquire an
instance of this class, we must go through the [OrgSync][os_class] static class to call one of the
`newApiClient` methods.  These methods take your secret API key and connect to an instance of the
OrgSync API servers.  For instance, to create an instance of the API client where your API key is
stored in a system property, you could do something like this:

    import com.orgsync.api.ApiClient;
    import com.orgsync.api.OrgSync;
    
    ...
    
      String apiKey = System.getProperty(API_KEY_PROPERTY);
      ApiClient client = OrgSync.newApiClient(apiKey);
      
      ...
      
      client.destroy();

With an instance of the `ApiClient`, you can request a resource to gain access to the API (More on this later).

Finally, to free the resources used by the `ApiClient`, a user must call `client.destroy` to free any threads that
are used by the underlying HTTP client.

## Advanced Usage

A default instance of the [AsyncHttpClient][async_client] is created to use to communicate to the OrgSync
servers.  For most cases this will be sufficient.  However, in some circumstances it may be necessary to
configure the `AsyncHttpClient` instance.  For these cases, we simply provide a way to set the HTTP client
using `client.setHttpClient(httpClient)` method.  This will make the `ApiClient` use the given HTTP client.

One example where this may be necessary is the use of an HTTP proxy.  The following example shows a simple
case where we create a new `AsyncHttpClient` instance, configure it to use a proxy, and make our `ApiClient`
use this instance:

    ProxyServer proxy = new ProxyServer("my.proxy.host", 8080);
    AsyncHttpClientConfig config = new AsyncHttpClientConfig.Builder().
            setProxyServer(proxy).
             build();
    AsyncHttpClient httpClient = new AsyncHttpClient(config);

    apiClient.setHttpClient(httpClient);

For more information on configuring the Async HTTP Client, see their [docs][async_http].

  [api_client]: 
  [os_class]: 
  [async_client]: 
  [async_http]: http://sonatype.github.io/async-http-client/
  