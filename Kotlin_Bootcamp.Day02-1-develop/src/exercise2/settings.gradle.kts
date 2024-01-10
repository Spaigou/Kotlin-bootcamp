plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "exercise2"
include("src:main:data")
findProject(":src:main:data")?.name = "data"
