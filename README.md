# pants-proto-test
Test of pants protobuf code generation with Python and Java

Overview: We are trying to build a schema registry (collection of `.proto` files) and build packages of the compiled objects in both Python and Java. We need these packages to be available outside the repo, so we are using `python_distribution` and `deploy_jar`.

The problem: We can make either of the packages work independnently. If we exclude `pants.backend.codegen.protobuf.python` from the backends (and remove the Python package), then the Java package compiles correctly and includes everything expected - compiled protobuf as well as the `com.google.protobuf` library. 

However, when we include both Python and Java codegen, we become unable to build the Java package, with this error:

```
❯ ./pants package schemas/com/example/schemas/v1:schemas_java
09:37:26.00 [INFO] Initializing scheduler...
09:37:27.26 [INFO] Scheduler initialized.
09:37:27.32 [WARN] Please either set `enabled = true` in the [anonymous-telemetry] section of pants.toml to enable sending anonymous stats to the Pants project to aid development, or set `enabled = false` to disable it. No telemetry sent for this run. An explicit setting will get rid of this message. See https://www.pantsbuild.org/v2.15/docs/anonymous-telemetry for details.
09:37:29.14 [ERROR] 1 Exception encountered:

Engine traceback:
  in `package` goal
  in Compile with javac
  in Compile with javac
  in Compile with javac
  in Compile with javac

ClasspathSourceMissing: No JVM classpath providers (from: CompileJavaSourceRequest, CoursierFetchRequest, DeployJarClasspathEntryRequest, JvmResourcesRequest) were compatible with the combination of inputs:
  * 3rdparty/python:reqs#protobuf       (python_requirement)
```

Separately, in both cases, we cannot import the package in `/schemas` from another root, but I assume this is me doing something wrong.

Another oddity is that with both codegen backends, when we do `./pants export-codegen ::`, only the Java is exported:
```
❯ tree dist/codegen
dist/codegen
└── schemas
    └── com
        └── example
            └── schemas
                └── v1
                    └── PersonOuterClass.java

5 directories, 1 file
```
