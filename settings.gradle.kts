pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {

            library("netty-handler", "io.netty:netty-handler:4.1.118.Final")
            library("netty-codec-http2", "io.netty:netty-codec-http2:4.1.108.Final")
            library("commons-io", "commons-io:commons-io:2.14.0")
            library("bcprov", "org.bouncycastle:bcprov-jdk18on:1.78")
            library("protobuf-java", "com.google.protobuf:protobuf-java:3.25.5")
            library("gson", "com.google.code.gson:gson:2.8.9")
            library("guava", "com.google.guava:guava:32.0.1-android")
            library("commons-compress", "org.apache.commons:commons-compress:1.26.0")
            library("okio", "com.squareup.okio:okio:3.4.0")
            library("okio-jvm", "com.squareup.okio:okio-jvm:3.4.0")
            
        }
    }
}

rootProject.name = "flowers_team"
include(":app")
