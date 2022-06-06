package com.example.rdxapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.ui.onboarding.HomeFragment.Companion.SIGN_UP_FLOW
import com.example.rdxapp.utils.SingleLiveEvent
import com.example.rdxapp.utils.ViewModelAssistedFactory
import javax.inject.Inject

class HomeViewModel(
    private val profileRepository: ProfileRepository,
    handle: SavedStateHandle
) : ViewModel() {

    private val signUpFlow = handle.get<Boolean>(SIGN_UP_FLOW) ?: false

    private val _userName = MutableLiveData<Pair<String, String>>()
    val userName: LiveData<Pair<String, String>>
        get() = _userName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String>
        get() = _phoneNumber

    private val _logout = SingleLiveEvent<Unit>()
    val logout: SingleLiveEvent<Unit>
        get() = _logout

    private val _unlockPrompt = SingleLiveEvent<Unit>().apply {
        if (!signUpFlow) {
            value = Unit
        }
    }
    val unlockPrompt: SingleLiveEvent<Unit>
        get() = _unlockPrompt

    private val _pinIncorrect = MutableLiveData<Boolean>()
    val pinIncorrect: LiveData<Boolean>
        get() = _pinIncorrect

    init {
        _userName.value = Pair(profileRepository.firstName, profileRepository.lastName)
        _email.value = profileRepository.email
        _phoneNumber.value = profileRepository.phoneNumber
    }

    fun checkPin(pin: String) {
        _pinIncorrect.value = profileRepository.pin != pin
    }

    fun logoutClicked() {
        profileRepository.pin = ""
        _logout.value = Unit
    }
}

class HomeViewModelFactory @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModelAssistedFactory<HomeViewModel> {
    override fun create(handle: SavedStateHandle): HomeViewModel {
        return HomeViewModel(profileRepository, handle)
    }
}