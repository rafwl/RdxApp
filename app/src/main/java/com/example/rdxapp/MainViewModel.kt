package com.example.rdxapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.data.Result
import com.example.rdxapp.utils.SingleLiveEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _goNext = MutableLiveData<Int>()
    val goNext: LiveData<Int>
        get() = _goNext

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean>
        get() = _progressVisible

    init {
        viewModelScope.launch {
            profileRepository.fetchProfile().collect {
                when (it.status) {
                    Result.Status.SUCCESS -> {
                        _progressVisible.value = false
                        it.data?.let { pin ->
                            _goNext.value =
                                if (pin.isNotEmpty()) R.id.navigationHome else R.id.navigationWelcome
                        }
                    }
                    Result.Status.LOADING -> {
                        _progressVisible.value = true
                    }
                    Result.Status.ERROR -> {
                        _progressVisible.value = false
                    }
                }
            }
        }
    }
}