# CWE-117 Java Encoder Experiment

## Running

```shell
docker run -it --rm -v "$(pwd)":/app -v "$(pwd)/mvn-repo":/root/.m2 -p 8080:8080 -w /app maven mvn spring-boot:run
```

Browse to <http://127.0.0.1:8080/?untrustedInput=testKey>

## Packaging

```shell
rm -rf target
docker run -it --rm -v "$(pwd)":/app -v "$(pwd)/mvn-repo":/root/.m2 -w /app maven mvn clean compile package
```
