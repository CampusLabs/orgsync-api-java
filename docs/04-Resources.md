# Working with Resources

Once we have an [ApiClient][api_client] we can access the [Resources][resources] of the API.
Each resource has its own interface that should match the resources shown in the
[Orgsync API V2 docs][os_api], but they all share a few common patterns which we will discuss 
here.

## Handling Responses

Looking at the return type for any of the various `Resources` can be a little daunting at first.
We make liberal use of Java [generics][generics] to ensure as much code reuse as possible.  As
a result, you end up with a return type like `ListenableFuture<ApiResponse<List<Account>>>`.  We 
will use this as an example for this section (and its returned by [AccountsResource.getAccounts()][get_accounts]).
Let's break this down into pieces.

### ListenableFuture

The [ListeneableFuture][listenable_future] type comes from the [Async HTTP Client][async_http] library.
A [Future][future] is a concept that is used in [asynchronous programming][async] to allow clients
to make a request, return immediately, and then get the response at a later time.

If you are unfamiliar with async APIs and would like to use this like a normal blocking API, you can
simply call the `get()` method on the future, which blocks until the result is available:

    import com.orgsync.api.AccountsResource;
    import com.orgsync.api.ApiResponse;
    import com.orgsync.api.Resources;
    
    ...
    
    AccountsResource accountsResource = client.getResource(Resources.ACCOUNTS);
    
    // NOTE: Call get() immediately to use in a blocking style 
    // This code doesn't need to use the ListenableFuture
    ApiResponse<List<Account>> accountsResponse = accountsResource.getAccounts().get();

    // work with ApiResponse of accountsResponse

## ApiResponse

The [ApiResponse][api_response] class simply handles the fact that any API call can either return
a successful response or an error of some sort (For instance requesting an account id that doesn't
exist).  Because of this, every resource returns an `ApiResponse` to handle both of these cases.

The first thing we must do is determine whether or not the call was successful.  To do this, we simply 
call the `isSuccess()` method of the `ApiResponse`.  If this returns `true`, then we can get the
result with the correct type from `getResult()`.  Otherwise, we had a failure, and we can get the
[ApiError][api_error] by calling `getError()`.  The `ApiError` has the message returned from the
API, which can be retrieved using `getMessage()`.  See this simple example:

    import com.orgsync.api.ApiError;
    import com.orgsync.api.ApiResponse;

    ... // continuing from above
    
    boolean successful = accountsResponse.isSuccess();
    
    if(successful) {
      // successful response with accounts, lets do something with them.
      List<Account> accounts = accountsResponse.getResult();
      handleAccount(accounts);
    } else {
      // oh no, a failure
      ApiError error = accountsResponse.getError();
      logger.error("Failed to retrieve accounts!  Message=" + error.getMessage());
    }
    
    ...
    
## Models

The final piece of the puzzle is the `List<Account>`.  All resources return an `ApiResponse` that
is either a model type, or a [java.util.List][jul] of some model type.  These models are based on
the returned JSON that is documented in the [OrgSync API V2 docs][os_api].  Because we want the
docs on the website to remain the one source of truth for the API, very little javadoc is provided
for most of the model classes.  Please see the docs online for interpretation and possible values
of each field in the models.

  [api_client]: 
  [os_api]: https://api.orgsync.com/api/docs/v2
  [generics]: http://en.wikipedia.org/wiki/Generics_in_Java
  [get_accounts]: 
  [listenable_future]: http://sonatype.github.io/async-http-client/apidocs/reference/com/ning/http/client/ListenableFuture.html
  [async_http]: http://sonatype.github.io/async-http-client/
  [future]: http://en.wikipedia.org/wiki/Future_(programming)
  [async]: 
  [api_response]: 
  [api_error]: 
  [jul]: http://docs.oracle.com/javase/6/docs/api/java/util/List.html