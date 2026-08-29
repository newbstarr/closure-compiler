package com.google.javascript.closure.example;

import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.WarningLevel;

import java.util.Collections;
import java.util.List;

public final class ClosureCompilerService {

  private ClosureCompilerService() {
    // Utility class
  }

  public static String compile(String sourceCode) {
    Compiler compiler = new Compiler();
    CompilerOptions options = new CompilerOptions();

    CompilationLevel.ADVANCED_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
    WarningLevel.DEFAULT.setOptionsForWarningLevel(options);

    List<SourceFile> externs = CommandLineRunner.getDefaultExterns();
    List<SourceFile> inputs = Collections.singletonList(SourceFile.fromCode("source.js", sourceCode));

    Result result = compiler.compile(externs, inputs, options);

    if (!result.success) {
      throw new IllegalStateException("Closure Compiler failed with "
          + result.errors.size() + " errors and "
          + result.warnings.size() + " warnings.\n" + result.errors);
    }

    return compiler.toSource();
  }
}
