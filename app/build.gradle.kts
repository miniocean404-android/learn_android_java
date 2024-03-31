import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties
import java.util.TimeZone

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    // 对应 gradle 配置： apply plugin: 'xxx'
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}


val keystorePropFile = rootProject.file("app/key.properties")
val keystoreProps = Properties().apply {
    load(keystorePropFile.inputStream())
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
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }
    }

    sourceSets {
        // 指定本地三方依赖位置(有问题)
        // getByName("main") {
        //    jniLibs.srcDirs("libs")
        // }
    }

    signingConfigs {
        register("release") {
            keyAlias = keystoreProps["keyAlias"] as String
            keyPassword = keystoreProps["keyPassword"] as String
            storeFile = file(keystoreProps["storeFile"] as String)
            storePassword = keystoreProps["storePassword"] as String
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            signingConfig = signingConfigs.getByName("release")

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


    // Compose 版本
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // 打包名称配置
    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                if (variant.buildType.name == "release") {
                    val dateFormat = SimpleDateFormat("yyyyMMddHHmm")
                    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Shanghai")
                    val date = dateFormat.format(Date())
                    val outputFileName =
                        "学习安卓开发-${variant.baseName}-${variant.versionName}-${variant.versionCode}-${date}.apk"
                    output.outputFileName = outputFileName
                }
            }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Compose 依赖
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // 添加 recycler_view 依赖
    implementation(libs.androidx.recyclerview)

    // retrofit 封装了 okhttp，并且引入它同时也会引入 okhttp
    // retrofit2.converter Gson 是 retrofit 的 Bean 转换器
    // adapter.rxjava3 解决嵌套请求回调地狱问题
    implementation(libs.com.squareup.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.adapter.rxjava3)

    // ijkplayer
    implementation(libs.tv.ijkplayer.java)
    implementation(libs.tv.ijkplayer.armv7a)
    implementation(libs.ijkplayer.arm64)
    implementation(libs.ijkplayer.x86)
    implementation(libs.ijkplayer.x8664)

    // 可选
    // implementation("tv.danmaku.ijk.media:ijkplayer-armv5:0.8.8")

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

    // 图片优化加载库
    implementation(libs.glide2)
    // ksp(libs.glide2.ksp)

    // 高德地图
    implementation(files("libs/arr/AMap3DMap_10.0.600_AMapSearch_9.7.1_AMapLocation_6.4.3_20240314.aar"))


    // 图片内存优化 fresco
    implementation(libs.fresco)
    // For animated GIF support
    implementation(libs.fresco.animated.gif)
    // For WebP support, including animated WebP
    implementation(libs.fresco.animated.webp)
    implementation(libs.fresco.webpsupport)
    // For WebP support, without animations
    implementation(libs.fresco.facebook.webpsupport)
    // Provide the Android support library (you might already have this or a similar dependency)
    implementation(libs.fresco.support.core.utils)

    // GIF drawable
    implementation(libs.android.gif.drawable)

    // json 序列化
    implementation(libs.google.gson)

    // Rx
    implementation(libs.io.reactivex.rxjava3.rxandroid)
    implementation(libs.reactivex.rxjava)
    implementation(libs.rxjava3.rxkotlin)

    // 图片选择器
    implementation(libs.pictureselector)
    implementation(libs.compress)
    implementation(libs.ucrop)
    implementation(libs.github.camerax)

    // room
    implementation(libs.androidx.room.room.runtime)
    ksp(libs.androidx.room.compiler)
}

