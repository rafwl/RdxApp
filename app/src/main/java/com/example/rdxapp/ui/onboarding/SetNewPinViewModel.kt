package com.example.rdxapp.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rdxapp.utils.SingleLiveEvent

class SetNewPinViewModel : ViewModel() {

    private val _goNext = SingleLiveEvent<String>()
    val goNext: SingleLiveEvent<String>
        get() = _goNext

    private val _validInput = MutableLiveData<Boolean>()
    val validInput: LiveData<Boolean>
        get() = _validInput

    private var pin = ""

    fun onPinChanged(pin: String) {
        this.pin = pin
        _validInput.value = pin.isNotEmpty()
    }

    fun nextButtonClicked() {
        _goNext.value = pin
    }
}