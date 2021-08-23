# OTUS.Epam-project
Epam Project work for Otus course Java QA Engineer

## Set up
To run this project you need to set value to next properties:

* browserName, type of browser you're going to use, e.g."chrome"
* isDriverRemote, "true" to run tests in Selenoid, "false" to run tests on local host
* Use the next command in command line:
```bash
mvn clean test -Dbrowser="[browserName]" -DisDriverRemote="[isDriverRemote]"
```