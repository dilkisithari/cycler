import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

// Load secrets from local.properties (never committed to git).
val localProps = Properties().apply {
    val f = rootProject.file("local.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}
val mapsApiKey: String = localProps.getProperty("MAPS_API_KEY", "")
val payhereMerchantId: String = localProps.getProperty("PAYHERE_MERCHANT_ID", "")

android {
    namespace = "lk.nd.cycler"
    compileSdk = 35

    defaultConfig {
        applicationId = "lk.nd.cycler"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Injected into AndroidManifest.xml as ${MAPS_API_KEY}
        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
        // Exposed to Java as BuildConfig.PAYHERE_MERCHANT_ID
        buildConfigField("String", "PAYHERE_MERCHANT_ID", "\"$payhereMerchantId\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.annotation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.bumptech.glide:glide:4.16.0") // Glide dependency
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0") // For annotation processing
    implementation("com.google.android.gms:play-services-maps:19.1.0")
    implementation("com.github.PayHereDevs:payhere-android-sdk:v3.0.17")
    implementation("com.google.code.gson:gson:2.11.0")
}