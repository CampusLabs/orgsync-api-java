# Java OrgSync API Client

[![Build Status][]][1]

Java REST client for the OrgSync API

## Dev setup

- **Eclipse:** run `gradle eclipse` to create the eclipse files and then import into eclipse
- **IntelliJ:** read [this](http://blogs.jetbrains.com/idea/2013/04/gradle-improvements-at-121/)
- **NetBeans:** read [this](http://plugins.netbeans.org/plugin/41776/gradle)
- Others probably available. Check out the gradle [documentation](http://www.gradle.org/documentation)

## TODO

* Include Joda for local date?  Or just use string and let clients handle?
* Include apache commons for toStringBuilder, equalsBuilder, etc?  Or just keep generating?
* Models aren't versioned with the api...
* Add timeout and retry

  [1]: https://circleci.com/gh/orgsync/orgsync-api-java "Build Status"
  [build status]: https://circleci.com/gh/orgsync/orgsync-api-java.png?circle-token=672ada70345a132d9351bc9692f7b157d66ae137
