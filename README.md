[![Build Status](https://travis-ci.org/hernandazevedo/starwarsshop.svg?branch=master)](https://travis-ci.org/hernandazevedo/starwarsshop)
[![codecov.io](https://codecov.io/gh/hernandazevedo/starwarsshop/branch/master/graph/badge.svg)](https://codecov.io/gh/hernandazevedo/starwarsshop)
[![Coverage Status](https://coveralls.io/repos/github/hernandazevedo/starwarsshop/badge.svg?branch=master)](https://coveralls.io/github/hernandazevedo/starwarsshop?branch=master)
# starwarsshop
Android - MVP + Dagger 2 + RxJava + Retrofit2 - Test Coverage

This project is a Proof of concept for the Model View Presenter architecture for Android.

# Unit tests coverage reports

Codecov parses uploaded test coverage reports but your project is required to generate them first.
You can use [jacoco-android-gradle-plugin](https://github.com/arturdm/jacoco-android-gradle-plugin)
to create appropriate gradle tasks and run this command to generate the reports:

```
./gradlew jacocoTestReport
```

Running the command below generates the reports: 

```
./gradlew connectedCheck
```

# Configure Travis CI

If you use [Travis CI](https://travis-ci.org) as your continuous integration server you can
configure it to build the project, generate test coverage reports and upload them to
[Codecov](https://codecov.io). See an example [.travis.yml](.travis.yml) file on how to do this.