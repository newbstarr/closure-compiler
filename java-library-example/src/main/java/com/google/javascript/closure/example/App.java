package com.google.javascript.closure.example;

public class App {
  public static void main(String[] args) {
    String unminifiedJs = "function hello(name) { console.log('Hello, ' + name); } hello('World');";
    String compiledJs = ClosureCompilerService.compile(unminifiedJs);
    System.out.println("Compiled JavaScript output:\n" + compiledJs);
  }
}
