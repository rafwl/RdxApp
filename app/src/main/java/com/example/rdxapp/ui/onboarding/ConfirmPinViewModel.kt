package com.example.rdxapp.ui.onboarding

import androidx.lifecycle.*
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.ui.onboarding.ConfirmPinFragment.Companion.PIN
import com.example.rdxapp.utils.SingleLiveEvent
import com.example.rdxapp.utils.ViewModelAssistedFactory
import javax.inject.Inject

class ConfirmPinViewModel(
    private val profileRepository: ProfileRepository,
    handle: SavedStateHandle
) : ViewModel() {

    private val selectedPin = handle.get<String>(PIN) ?: ""

    private val _goNext = SingleLiveEvent<Unit>()
    val goNext: SingleLiveEvent<Unit>
        get() = _goNext

    private val _pinsDifferent = SingleLiveEvent<Unit>()
    val pinsDifferent: SingleLiveEvent<Unit>
        get() = _pinsDifferent

    private val _validInput = MutableLiveData<Boolean>()
    val validInput: LiveData<Boolean>
        get() = _validInput

    var pin = ""

    fun onPinChanged(pin: String) {
        this.pin = pin
        _validInput.value = pin.isNotEmpty()
    }

    fun nextButtonClicked() {
        if (selectedPin == pin) {
            profileRepository.pin = selectedPin
            _goNext.value = Unit
        } else {
            _pinsDifferent.value = Unit
        }
    }
}

class ConfirmPinViewModelFactory @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModelAssistedFactory<ConfirmPinViewModel> {
    override fun create(handle: SavedStateHandle): ConfirmPinViewModel {
        return ConfirmPinViewModel(profileRepository, handle)
    }
}