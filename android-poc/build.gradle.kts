// Root project build file: avoid adding plugin classpath here to prevent conflicts with plugins DSL
// Repositories for all projects (include mirrors to help dependency resolution)
allprojects {
    repositories {
        google()
        mavenCentral()

        // Mirrors helpful in restricted networks
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://mirrors.tencent.com/nexus/repository/maven-public/") }

        // Fallback repos
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/releases/") }
    }
}
