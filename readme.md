# BSC test application
## Run from IDE
as standard spring boot application or via maven:
```
clean spring-boot:run
``` 
Application needs run-argument with name of file which contains data. Run-argument can be multiple like this:
```
--data=payments1 --data=payments2 --data=payments4
```

Files are stored in `resources/storage`

## Run from command line
1. Compile jar: 
```
mvn clean package
```
2. Run jar:
```
java -jar bsc-test-0.0.1-SNAPSHOT.jar --data=payments1 --data=payments2 --data=payments4
```
Files are stored in jar.
