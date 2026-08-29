# Closure Compiler Java Library Example

This module demonstrates how to use Closure Compiler via Java API from an application (eg. Tomcat service) without using CLI invocation.

## Build

1. Build and install the Closure Compiler artifacts from this repository into local Maven cache:

```bash
cd <repo-root>/java-library-example
../maven/pom.xml is in ../maven/
../maven/ (top-level maven module) should be built first
cd ..
./java-library-example/build.sh
```

2. Run the example (prints compiled JS):

```bash
cd java-library-example
mvn -q exec:java
```

## Java library usage

Add this dependency in your application `pom.xml`:

```xml
<dependency>
  <groupId>com.google.javascript</groupId>
  <artifactId>closure-compiler</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

If you prefer a released version from Maven Central, use the latest release coordinate instead.

## Example code usage

```java
import com.google.javascript.closure.example.ClosureCompilerService;

String unminified = "function hello(name){ console.log('Hi '+name); } hello('Tom');";
String minified = ClosureCompilerService.compile(unminified);
```

## Tomcat server integration

- Do this in a background worker or servlet thread; Closure Compiler is CPU-heavy and should not run on request threads with high concurrency.
- Cache compile results using keys (source hash + options) to avoid duplicate expensive work.
- Use `ClosureCompilerService` as the core helper class in your webapp.
