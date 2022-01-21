plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo3").version("3.0.0")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk  = 31

    defaultConfig {
        applicationId = "com.mishka.graphqltest"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.fragment:fragment-ktx:1.4.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.3.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    val roomVersion = "2.4.1"

    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$roomVersion")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$roomVersion")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$roomVersion")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$roomVersion")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$roomVersion")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:2.4.1")

    implementation("com.apollographql.apollo3:apollo-runtime:3.0.0")
    implementation("com.apollographql.apollo3:apollo-rx3-support:3.0.0")


    implementation("com.google.dagger:hilt-android:2.38.1")
    implementation("com.google.firebase:firebase-common-ktx:20.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation(platform("com.google.firebase:firebase-bom:29.0.3"))
    implementation("com.google.firebase:firebase-crashlytics")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.activity:activity-ktx:1.4.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")




    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    testImplementation("com.google.truth:truth:1.1.3")

    implementation("io.reactivex.rxjava3:rxjava:3.1.3")

    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")

    implementation("com.facebook.fresco:fresco:2.6.0")


}

apollo {
    packageName.set("com.mishka.graphqltest.apollo")
}


kapt {
    correctErrorTypes = true
}