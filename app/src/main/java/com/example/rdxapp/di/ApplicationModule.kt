package com.example.rdxapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.rdxapp.RDXApplication
import com.example.rdxapp.data.ProfileRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: RDXApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideProfileRepository(sharedPreferences: SharedPreferences): ProfileRepository
        = ProfileRepository(sharedPreferences)

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey
            .Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            SHARED_PREFS_FILENAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    companion object {
        const val SHARED_PREFS_FILENAME = "com.example.rdxapp.android_preferences"
    }
}