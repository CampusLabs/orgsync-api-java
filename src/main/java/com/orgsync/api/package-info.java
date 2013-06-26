/**
 * <h3> Orgsync API (V2) </h3>
 * 
 * This package provides access to the OrgSync API version 2.  The 
 * {@link com.orgsync.api.OrgSync} class provides access to the 
 * clients that can be used with the API.  While many part of this 
 * library are well documented, others are left intentionally vauge 
 * since the API documentation should be consulted:
 * <p>
 * <a href="https://api.orgsync.com/api/docs/v2">https://api.orgsync.com/api/docs/v2</a>
 * 
 * <h4> Retrieving a Client </h4>
 * 
 * To retrieve a client, use the {@link com.orgsync.api.OrgSync#newApiClient(String)} method 
 * and pass in your OrgSync API key.  The returned client provides access 
 * to the various resources provided by the API.  
 * <p>
 * The client uses the <a href="https://github.com/AsyncHttpClient/async-http-client">
 * Async HTTP Client</a> under the hood to handle the communication with the REST API.  
 * A default client is created by the client.  However, if a user needs to customize the 
 * settings for this client, a new HTTP client can be set using the 
 * {@link com.orgsync.api.ApiClient#setHttpClient(com.ning.http.client.AsyncHttpClient)} 
 * method.  Most users of this API will not need to use this.
 * 
 * <h4> Accessing Resources </h4>
 * 
 * The various {@link com.orgsync.api.Resource}s provide access to the data available 
 * in the API.  The {@link com.orgsync.api.ApiClient#getResource(Resource)} call is 
 * used to retrieve these resources.  The list of possible resources to access are 
 * defined in the {@link com.orgsync.api.Resources} class as constants.  Each 
 * {@link com.orgsync.api.Resource} returns a different type based on its 
 * parameterized type.
 * 
 * <h4> Accessing Data from a Resource </h4>
 * 
 * Each resource has methods that match the various endpoints that are made available 
 * in the <a href="https://api.orgsync.com/api/docs/v2">API documentation</a>.  However, 
 * there are some common patterns that are used by each of the resources.
 * <p>
 * All calls return a {@link com.ning.http.client.ListenableFuture} of a 
 * {@link com.orgsync.api.ApiResponse}.  The future is just a handle to work that 
 * will be available at some point in the future.  To block and wait for the response, 
 * simply call the {@link com.ning.http.client.ListenableFuture#get()} method.  A 
 * {@link java.util.concurrent.Future} is returned so that users that want to make the 
 * application fully asynchronous have that options.  Otherwise, using the 
 * {@link com.ning.http.client.ListenableFuture#get()} method results in a normal blocking 
 * style of use.
 * <p> 
 * The {@link com.orgsync.api.ApiResponse} encapsulates the fact that any call to the API
 * can fail for many reasons (e.g. passing an invalid ID).  For this reason, any call 
 * can return either a success or a failure.  The {@link com.orgsync.api.ApiResponse#isSuccess()} 
 * method is used to determine if the call was successful.  If it was, the correct result 
 * is available using the {@link com.orgsync.api.ApiResponse#getResult()} method.  If the 
 * call failed (meaning {@link com.orgsync.api.ApiResponse#isSuccess()} is <code>false</code>), 
 * then the {@link com.orgsync.api.ApiResponse#getError()} method returns an 
 * {@link com.orgsync.api.model.ApiError} which makes the message available using the 
 * {@link com.orgsync.api.model.ApiError#getMessage()} call.
 * <p>
 * A call to the API can fail for other reasons (e.g. networking issues).  If for any reason 
 * the API client cannot reach the API, a {@link com.orgsync.api.ApiClientException} will be 
 * thrown and the underlying exception can be retrieved using {@link java.lang.Throwable#getCause()}.
 * 
 * <h4> Making Updates </h4>
 * 
 * Some resources allow various fields to be updated.  If all fields are required for an update, 
 * then generally they are required as parameters to the update method.  If only a few of 
 * many fields can be accessed then a request object may be required.  For instance, to update 
 * an account a user can use the {@link com.orgsync.api.model.accounts.AccountUpdateRequest} 
 * object.  This class provides various setter methods for the data that can be updated (e.g. 
 * {@link com.orgsync.api.model.accounts.AccountUpdateRequest#setFirstName(String)}).  Each 
 * of these setters returns the object so that they can be fluently chained:
 * <p>
 * <code>
 *   AccountUpdateRequest request = new AccountUpdateRequest().setFirstName("Joan").setLastName("Smith")
 * </code>
 */
package com.orgsync.api;