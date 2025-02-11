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
    id("kotlin-kapt")
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

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    sourceSets {
        // 指定本地三方依赖位置, 用于配置 so 文件
        // 配置需要指定 ndk 的类型文件夹，且添加位于此文件夹的上一级
        getByName("main") {
            jniLibs.srcDirs("libs/ijkplayer")
        }
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
            // 开启 R8 混淆, compose 开启这个就不咋卡顿了
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            signingConfig = signingConfigs.getByName("release")

            // 启用 R8 的代码缩减功能， 开启了 isMinifyEnabled 一定要配置混淆规则，如果不配置会导致找不到库对应的类文件
            isMinifyEnabled = true
            // 启用 R8 的资源缩减功能
            isShrinkResources = true


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
        // mvvm 数据绑定
        dataBinding = true
        // 启动视图绑定
        viewBinding = true
        // 开启 Jetpack Compose，哪里开启哪里需要指定 kotlinCompilerExtensionVersion
        compose = true
    }


    composeOptions {
        // Compose Compiler 版本
        kotlinCompilerExtensionVersion = "1.5.11"
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
                if (variant.buildType.name == "debug") {
                    output.outputFileName = variant.baseName + "_v" + versionName + "_" + variant.buildType.name + ".apk"
                }

                if (variant.buildType.name == "release") {
                    val dateFormat = SimpleDateFormat("yyyyMMddHHmm")
                    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Shanghai")
                    val date = dateFormat.format(Date())
                    val outputFileName =
                        "学习安卓开发-${variant.baseName}-${variant.versionName}-${variant.versionCode}-${date}.apk"
                    output.outputFileName = outputFileName
                }
            }

        // AndroidManifest 输出配置
//        variant.outputs[0].processManifest.doLast {
//            def manifestFile = "${manifestOutputDirectory}/AndroidManifest.xml"
//            def updatedContent = new File(manifestFile).getText('UTF-8')
//                .replaceAll("UMENG_APPKEY_VALUE", "5cb16d93570df399fd0014e2") // 友盟 AppKey
//                .replaceAll("QQ_APPID_VALUE", "100424468") // QQ AppId
//                .replaceAll("QQ_APPKEY_VALUE", "c7394704798a158208a74ab60104f0ba") // QQ Key
//                .replaceAll("WX_APPID_VALUE", "wxdc1e388c3822c80b") // 微信 AppId
//                .replaceAll("WX_APPKEY_VALUE", "3baf1193c85774b3fd9d18447d76cab0") // 微信 Key
//            new File(manifestFile).write(updatedContent, 'UTF-8')
//        }
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

    //本地依赖库：网络请求
    implementation(project(path=":http"))
    //本地依赖库：基础库
    implementation(project(path=":base"))

    // Jetpack Compose 依赖
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // compose 图标扩展依赖
    implementation(libs.androidx.material.icons.extended)
    // compose 导航
    implementation(libs.androidx.navigation.navigation.compose)
    // Jetpack Compose 网络图片加载
    implementation(libs.coil.kt.coil.compose)
    // Jetpack Compose ViewModel
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx2)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Jetpack Compose 约束布局
    implementation(libs.androidx.constraintlayout.compose)

    // Jetpack Compose 骨架屏
    implementation(libs.accompanist.placeholder.material)

    // Jetpack Compose 类似 SharedPreferences
    implementation(libs.androidx.datastore.datastore.preferences2)


    // 添加 recycler_view 依赖
    implementation(libs.androidx.recyclerview)

    // retrofit 封装了 okhttp，并且引入它同时也会引入 okhttp
    // retrofit2.converter Gson 是 retrofit 的 Bean 转换器
    // adapter.rxjava3 解决嵌套请求回调地狱问题
    implementation(libs.squareup.retrofit2)
    // 还有一种是 com.squareup.retrofit2:converter-moshi:2.11.0
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.converter.moshi)
    implementation(libs.adapter.rxjava3)

    // ijkplayer
    implementation(libs.tv.ijkplayer.java)

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
    implementation(fileTree("libs/amap"))

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

