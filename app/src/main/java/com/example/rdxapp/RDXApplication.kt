package com.example.rdxapp

import android.app.Application
import com.example.rdxapp.di.ApplicationComponent
import com.example.rdxapp.di.ApplicationModule
import com.example.rdxapp.di.DaggerApplicationComponent

class RDXApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)

    }
}