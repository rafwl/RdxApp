package com.example.rdxapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.ui.onboarding.ConfirmPinFragment.Companion.PIN
import com.example.rdxapp.ui.onboarding.ConfirmPinViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ConfirmPinViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var profileRepository: ProfileRepository

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    lateinit var viewModel: ConfirmPinViewModel

    @Test
    fun `when empty input, when inserted, verify invalid input`() {
        // given
        val previousPin = ""
        val currentPin = ""
        whenever(savedStateHandle.get<String>(PIN)).thenReturn(previousPin)
        viewModel = ConfirmPinViewModel(profileRepository, savedStateHandle)

        // when
        viewModel.onPinChanged(currentPin)

        // then
        Assert.assertEquals(viewModel.validInput.value, false)
    }

    @Test
    fun `when non empty input, when pin changed, verify valid input`() {
        // given
        val previousPin = "1234"
        val currentPin = "1122"
        whenever(savedStateHandle.get<String>(PIN)).thenReturn(previousPin)
        viewModel = ConfirmPinViewModel(profileRepository, savedStateHandle)

        // when
        viewModel.onPinChanged(currentPin)

        // then
        Assert.assertEquals(viewModel.validInput.value, true)
    }

    @Test
    fun `when non empty input, when pins not the same, verify pins different`() {
        // given
        val previousPin = "1234"
        val currentPin = "1122"
        whenever(savedStateHandle.get<String>(PIN)).thenReturn(previousPin)
        viewModel = ConfirmPinViewModel(profileRepository, savedStateHandle)

        // when
        viewModel.onPinChanged(currentPin)
        viewModel.nextButtonClicked()

        // then
        Assert.assertEquals(viewModel.pinsDifferent.value, Unit)
    }

    @Test
    fun `when non empty input, when pins the same, verify go next`() {
        // given
        val previousPin = "1234"
        val currentPin = "1234"
        whenever(savedStateHandle.get<String>(PIN)).thenReturn(previousPin)
        viewModel = ConfirmPinViewModel(profileRepository, savedStateHandle)

        // when
        viewModel.onPinChanged(currentPin)
        viewModel.nextButtonClicked()

        // then
        Assert.assertEquals(viewModel.goNext.value, Unit)
    }
}