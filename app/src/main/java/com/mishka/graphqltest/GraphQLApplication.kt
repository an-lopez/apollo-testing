package com.mishka.graphqltest

import android.app.Application
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GraphQLApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this);


        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }else{
            Timber.plant(CrashReportingTree())
        }
    }

    inner class CrashReportingTree: Timber.Tree(){
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }


            if (t != null) {
                if (priority == Log.ERROR) {
                    FirebaseCrashlytics.getInstance().recordException(t)
                } else if (priority == Log.WARN) {
                    FirebaseCrashlytics.getInstance().log(message)
                }
            }
        }

    }
}