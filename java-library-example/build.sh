#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Build closure-compiler from this repository into local Maven cache
pushd "${SCRIPT_DIR}/../maven" > /dev/null
mvn -DskipTests clean install
popd > /dev/null

# Build the example library module
pushd "${SCRIPT_DIR}" > /dev/null
mvn -DskipTests clean package
popd > /dev/null

echo "Build complete. Example jar is in java-library-example/target/closure-compiler-java-client-1.0-SNAPSHOT.jar"