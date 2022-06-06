package com.example.rdxapp.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.utils.SingleLiveEvent
import com.example.rdxapp.utils.isValidEmail
import javax.inject.Inject

class CredentialsViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _goNext = SingleLiveEvent<Unit>()
    val goNext: SingleLiveEvent<Unit>
        get() = _goNext

    private val _validInput = MutableLiveData<Boolean>()
    val validInput: LiveData<Boolean>
        get() = _validInput

    private var email = ""
    private var password = ""

    fun onEmailChanged(email: String) {
        this.email = email
        _validInput.value = email.isValidEmail() && password.isNotEmpty()
    }

    fun onPasswordChanged(password: String) {
        this.password = password
        _validInput.value = email.isNotEmpty() && password.isNotEmpty()
    }

    fun nextButtonClicked() {
        profileRepository.email = email
        profileRepository.password = password
        _goNext.postValue(Unit)
    }
}