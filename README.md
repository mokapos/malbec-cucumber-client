# cucumber-client-services

A client service to process cucumber json

#Merge JSON for rerun test
1. build jar
```
./gradlew jar
```
2. run jar

For merge. It's suitable for rerun. The cucumber json from first run as parent and from rerun as rerun.json
 ```
 java -jar malbec-[version].jar merge [path-cucumber-parent.json] [pat-cucumber-rerun.json]
 ```
 
For join. It's suitable for distributed test. the parameter is direcotry path that contains multiple json file
```
 java -jar malbec-[version].jar join smoke-testing-report
 ```
 **Minimum malbec version 2.0.0 can support join**
