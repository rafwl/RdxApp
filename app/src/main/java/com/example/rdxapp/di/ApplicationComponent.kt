package com.example.rdxapp.di

import com.example.rdxapp.MainActivity
import com.example.rdxapp.RDXApplication
import com.example.rdxapp.ui.onboarding.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class
])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(application: RDXApplication)
    fun inject(credentialsFragment: CredentialsFragment)
    fun inject(personalInfoFragment: PersonalInfoFragment)
    fun inject(setNewPinFragment: SetNewPinFragment)
    fun inject(confirmPinFragment: ConfirmPinFragment)
    fun inject(homeFragment: HomeFragment)
}