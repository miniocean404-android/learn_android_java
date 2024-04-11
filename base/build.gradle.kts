plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.miniocean.base"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        // 启动视图绑定
        viewBinding = true
        // 开启 Jetpack Compose
        compose = true
    }

}

dependencies {
    // kotlin核心库
    api(libs.androidx.core.ktx)

    // viewmodel
    api(libs.androidx.lifecycle.viewmodel.ktx)

    // 协程
    api(libs.org.jetbrains.kotlinx.coroutines.core)
    api(libs.org.jetbrains.kotlinx.coroutines.android)

    // compose相关
    api(libs.androidx.activity.compose)
    api(platform(libs.androidx.compose.bom))


    // lifecycle
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.rxlifecycle.components)

    // Androidx
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
    api(libs.androidx.appcompat)

    // utils工具库
    api(libs.utilcodex)

    //本地依赖库：网络请求
    api(project(path = ":http"))
    api(libs.androidx.databinding.runtime)

    api(libs.androidx.appcompat)
    api(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}