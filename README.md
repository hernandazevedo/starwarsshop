[![Build Status](https://travis-ci.org/hernandazevedo/starwarsshop.svg?branch=master)](https://travis-ci.org/hernandazevedo/starwarsshop)
[![Coverage Status](https://coveralls.io/repos/github/hernandazevedo/starwarsshop/badge.svg?branch=master)](https://coveralls.io/github/hernandazevedo/starwarsshop?branch=master)
<!--[![codecov.io](https://codecov.io/gh/hernandazevedo/starwarsshop/branch/master/graph/badge.svg)](https://codecov.io/gh/hernandazevedo/starwarsshop)
-->
# starwarsshop
Android - MVP + Dagger 2 + RxJava + Retrofit2 - Test Coverage

This project is a Proof of concept for the Model View Presenter architecture for Android.

# Unit tests coverage reports
<!--
Codecov parses uploaded test coverage reports but your project is required to generate them first.
You can use [jacoco-android-gradle-plugin](https://github.com/arturdm/jacoco-android-gradle-plugin)
to create appropriate gradle tasks and run this command to generate the reports:
-->

Run this command to generate the reports coverage files:
```
./gradlew jacocoTestCoverageReport
```

Running the command below generate and send the report files: 

```
./gradlew coveralls
```

# Configure Travis CI

If you use [Travis CI](https://travis-ci.org) as your continuous integration server you can
configure it to build the project, generate test coverage reports and upload them to
[Codecov](https://codecov.io). See an example [.travis.yml](.travis.yml) file on how to do this.

License
--------

    Copyright 2018 Hernand Azevedo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
