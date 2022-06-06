package com.example.rdxapp.data

import android.content.SharedPreferences
import com.example.rdxapp.api.resultFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun fetchProfile(): Flow<Result<String>> {
        return resultFlow(
            networkCall = {
                // Simulate network call and return data from sharedPrefs
                delay(1000)
                withContext(Dispatchers.Main) {
                    Result.success(pin)
                }
            },
            saveCallResult = {}
        )
    }

    var email: String
        get() {
            return sharedPreferences.getString(EMAIL, "") ?: ""
        }
        set(value) {
            sharedPreferences.edit().putString(EMAIL, value).apply()
        }

    var password: String
        get() {
            return sharedPreferences.getString(PASSWORD, "") ?: ""
        }
        set(value) {
            sharedPreferences.edit().putString(PASSWORD, value).apply()
        }

    var firstName: String
        get() {
            return sharedPreferences.getString(FIRST_NAME, "") ?: ""
        }
        set(value) {
            sharedPreferences.edit().putString(FIRST_NAME, value).apply()
        }

    var lastName: String
        get() {
            return sharedPreferences.getString(LAST_NAME, "") ?: ""
        }
        set(value) {
            sharedPreferences.edit().putString(LAST_NAME, value).apply()
        }

    var phoneNumber: String
        get() {
            return sharedPreferences.getString(PHONE_NUMBER, "") ?: ""
        }
        set(value) {
            sharedPreferences.edit().putString(PHONE_NUMBER, value).apply()
        }

    var pin: String
        get() {
            return sharedPreferences.getString(PIN, "") ?: ""
        }
        set(value) {
            sharedPreferences.edit().putString(PIN, value).apply()
        }

    companion object {
        private const val EMAIL = "preferences_email_key"
        private const val PASSWORD = "preferences_password_key"
        private const val FIRST_NAME = "preferences_first_name_key"
        private const val LAST_NAME = "preferences_last_name_key"
        private const val PHONE_NUMBER = "preferences_phone_number_key"
        private const val PIN = "preferences_pin_key"
    }
}