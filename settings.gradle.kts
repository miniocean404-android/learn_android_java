pluginManagement {
    repositories {
        // 镜像：https://blog.csdn.net/qq_57474766/article/details/132644097
        maven(url = uri("https://maven.aliyun.com/repository/google"))
        maven(url = uri("https://maven.aliyun.com/nexus/content/groups/public"))
        maven(url = uri("https://maven.aliyun.com/repository/jcenter"))
        // shiply 热修复
        maven(url = uri("https://tencent-tds-maven.pkg.coding.net/repository/shiply/repo"))

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
        maven(url = uri("https://maven.aliyun.com/repository/google"))
        maven(url = uri("https://maven.aliyun.com/nexus/content/groups/public"))
        maven(url = uri("https://maven.aliyun.com/repository/jcenter"))
        maven(url = uri("https://tencent-tds-maven.pkg.coding.net/repository/shiply/repo"))

        google()
        mavenCentral()
        // JitPack 是一个基于 GitHub 的Maven仓库
        maven(url = uri("https://jitpack.io"))
    }
}

rootProject.name = "LearnAndroidJava"
include(":app")
