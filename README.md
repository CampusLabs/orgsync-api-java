# Java OrgSync API Client

[![Build Status][]][1]

Java REST client for the [OrgSync API Version 2][os_api]

## Dev setup

- **Eclipse:** run `gradle eclipse` to create the eclipse files and then import into eclipse
- **IntelliJ:** read [this](http://blogs.jetbrains.com/idea/2013/04/gradle-improvements-at-121/)
- **NetBeans:** read [this](http://plugins.netbeans.org/plugin/41776/gradle)
- Others probably available. Check out the gradle [documentation](http://www.gradle.org/documentation)

## Integration Tests

To run integration tests you need a running instance of the OrgSync server.  The [db_template.conf][db_template] 
file provides the layout of the community we are testing against.  We need to load a staging database 
for the OrgSync server to be running against.  Here are the steps:

* `gradle generateDbTemplate` to create a db_template.json in the `build/` directory
* Make this file available to orgsync so you can create the template db (see `DB_TEMPLATE.md` in the OrgSync repo)
* Start the OrgSync server pointing at this database
* `gradle integrationTest` to run the tests against `localhost:8080`

  [1]: https://circleci.com/gh/orgsync/orgsync-api-java "Build Status"
  [build status]: https://circleci.com/gh/orgsync/orgsync-api-java.png?circle-token=672ada70345a132d9351bc9692f7b157d66ae137
  [os_api]: https://api.orgsync.com/api/docs/v2
  [db_template]: src/integration/resources/db_template.conf
  
