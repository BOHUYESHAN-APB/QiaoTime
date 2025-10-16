pluginManagement {
	repositories {
		// Primary plugin repositories
		gradlePluginPortal()
		google()
		mavenCentral()

		// Additional mirrors (helpful in China to speed up/avoid connection issues)
		maven { url = uri("https://maven.aliyun.com/repository/google") }
		maven { url = uri("https://maven.aliyun.com/repository/central") }
		maven { url = uri("https://mirrors.tencent.com/nexus/repository/maven-public/") }

			// Fallback repos: JitPack and Sonatype (some artifacts found here)
			maven { url = uri("https://jitpack.io") }
			maven { url = uri("https://s01.oss.sonatype.org/content/repositories/releases/") }
	}
}

// Enforce centralized repository configuration for project dependencies
// Use centralized repositories for dependency resolution. Avoid using RepositoriesMode API
// to keep compatibility with older Gradle distributions that may be used by Android Studio.
dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()

		// Mirrors (helpful in China)
		maven { url = uri("https://maven.aliyun.com/repository/google") }
		maven { url = uri("https://maven.aliyun.com/repository/central") }
		maven { url = uri("https://mirrors.tencent.com/nexus/repository/maven-public/") }

			// Fallback repos
			maven { url = uri("https://jitpack.io") }
			maven { url = uri("https://s01.oss.sonatype.org/content/repositories/releases/") }
	}
}

rootProject.name = "QiaoTimePOC"
include(":app")
