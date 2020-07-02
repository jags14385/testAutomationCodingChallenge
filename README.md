# testAutomationCodingChallenge

## Api Tests

- Copy the supplied env.json file into resources folder under `test\java` 
for the tests to run successfully

- Run tests by command : `./gradlew build test`


## Performance tests
- Used Intellij with the Scala plugin
- Used gradle for the build and run
- to run use `./gradlew gatlingRun` 
- Ensure Simulation Class Name anf File Name Match
- After successful run , you can find the reports at  
     `build/reports/gatling/randomloadsimulation-<timestamp>/index.html`
     
     Analysis of the Test Run :
     
     - 1 Request Time out
     - 95% of requests completed in less than 1830 ms
     & 99th percentile was 2266 ms.
     
     If there was expectation of minimum acceptable response time 
     we could have asserted it , as part of the simulation.
     
     - The whole test run 34 seconds 
     for a linear ramp of 1000 users over 25 seconds.
     