pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter() // <- this
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://www.jitpack.io" ) }
        google()
        mavenCentral()
        jcenter() // <- this
    }
}

rootProject.name = "CashApp"
include(":app")
 