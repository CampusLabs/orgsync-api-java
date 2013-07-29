# Overview

The Java API client library provides easy access to the [Orgsync API V2][os_api] from
the Java programming language.  This library simplifies access to the various resources
of the API by providing a consistent interface to the resources, handling error responses,
and providing simple Plain Old Java Objects (POJO) for the successful data returned.  Simply
add this library to your Java projects and profit from a well-tested and consistent interface
for accessing OrgSync resources.

## Javadoc

The full javadoc for the project can be found [here][javadoc].  While most of the core classes
are well documented, many of the resources and models are left intentionally vauge.  For full
information about the resources and their return values, please see the [Api Documentation][os_api].

## Source Code

For those that are interested, the code is open-source and available on [GitHub][code].  From
here, you can download the source code, create issues, or contribute to the project.

## Examples

Some example code can be found in the source [here][examples].  These simple examples show
how to create a client, access a resource, and make some simple requests.

## Implementation

The OrgSync API is a simple [RESTful][rest] [JSON][json] API.  As such, the resources from the
API are requested using an HTTP client and decoded using a JSON decoder.  For this project, we use
the [Async HTTP Client][async_http] to make our requests to the API endpoints.  This allows us
to provide an [asynchronous][async] interface for those that want it, but it is easy to use a
regular blocking style for those that don't.  To decode the JSON responses, we use the
[Google GSON][gson] library, which provides a very simple interface.  All logging is handled
by [SLF4J][slf4j], so your application can use any logging library that supports this interface.

## Testing

This library has a full suite of unit tests to help ensure high quality releases.  In addition, a
set of integration tests are run that exercises the client library against a controlled instance
of the OrgSync server to test that requests and responses work as expected.


  [os_api]: https://api.orgsync.com/api/docs/v2
  [code]: https://github.com/orgsync/orgsync-api-java
  [rest]: http://en.wikipedia.org/wiki/Representational_state_transfer
  [json]: http://en.wikipedia.org/wiki/Json
  [async_http]: http://sonatype.github.io/async-http-client/
  [gson]: https://code.google.com/p/google-gson/
  [slf4j]: http://www.slf4j.org/
  [examples]: https://github.com/orgsync/orgsync-api-java/tree/master/src/examples/java/com/orgsync/api/examples
  [javadoc]: 
  