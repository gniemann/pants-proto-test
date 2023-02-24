java_sources(
    name="java_lib",
    dependencies=["schemas/schemas/v1:protos"],
    sources=["**/*.java"]
)

deploy_jar(
    name="schemas_java",
    dependencies=[":java_lib"],
    main=""
)
