package com.example.rdxapp.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.utils.SingleLiveEvent
import javax.inject.Inject

class PersonalInfoViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _goNext = SingleLiveEvent<Unit>()
    val goNext: SingleLiveEvent<Unit>
        get() = _goNext

    private val _validInput = MutableLiveData<Boolean>()
    val validInput: LiveData<Boolean>
        get() = _validInput

    private var firstName = ""
    private var lastName = ""
    private var phoneNumber = ""

    fun onFirstNameChanged(firstName: String) {
        this.firstName = firstName
        _validInput.value = firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && phoneNumber.isNotEmpty()
    }

    fun onLastNameChanged(lastName: String) {
        this.lastName = lastName
        _validInput.value = firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && phoneNumber.isNotEmpty()
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        _validInput.value = firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && phoneNumber.isNotEmpty()
    }

    fun nextButtonClicked() {
        profileRepository.firstName = firstName
        profileRepository.lastName = lastName
        profileRepository.phoneNumber = phoneNumber
        _goNext.value = Unit
    }
}