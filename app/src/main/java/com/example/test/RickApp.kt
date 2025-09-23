package com.example.test

import android.app.Application
import com.example.test.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickApp: Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			modules(appModule)
			androidContext(this@RickApp)
		}
	}
}