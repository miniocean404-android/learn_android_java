plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.learnandroidjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.learnandroidjava"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86")
            abiFilters.add("x86_64")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // 添加 recycler_view 依赖
    implementation(libs.androidx.recyclerview)

    // okhttp
    implementation(libs.com.squareup.okhttp3.okhttp)

    // ijkplayer
    implementation(libs.tv.ijkplayer.java)
    implementation(libs.tv.ijkplayer.armv7a)
    implementation(libs.ijkplayer.arm64)
    implementation(libs.ijkplayer.x86)
    implementation(libs.ijkplayer.x8664)
    // 可选
    //implementation("tv.danmaku.ijk.media:ijkplayer-armv5:0.8.8")

    // xCrash Android - 捕获 Java 崩溃，native 崩溃和 ANR 的能力
    implementation(libs.xcrash)

    // 二维码扫码、解码、生成库
    implementation(libs.zxing)

    // key-value 存储库
    implementation(libs.mmkv)

    // 轮播图
    implementation(libs.banner)

    // 4.4 以上状态栏，导航栏沉浸
    implementation(libs.com.geyifeng.immersionbar.immersionbar3)
    implementation(libs.com.geyifeng.immersionbar.immersionbar.ktx)

    // 权限请求框架
    implementation(libs.xxpermissions)

    // toast
    implementation(libs.toaster)

    // 手机查看崩溃日志
    implementation(libs.spiderman)
}