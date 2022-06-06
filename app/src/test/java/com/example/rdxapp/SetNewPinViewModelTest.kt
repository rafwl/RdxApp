package com.example.rdxapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rdxapp.ui.onboarding.SetNewPinViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SetNewPinViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: SetNewPinViewModel

    @Test
    fun `given empty pin, when inserted, verify invalid input`() {
        // given
        val pin = ""
        viewModel = SetNewPinViewModel()

        // when
        viewModel.onPinChanged(pin)

        // then
        Assert.assertEquals(viewModel.validInput.value, false)
    }

    @Test
    fun `given nonempty pin, when inserted, verify valid input`() {
        // given
        val pin = "1234"
        viewModel = SetNewPinViewModel()

        // when
        viewModel.onPinChanged(pin)

        // then
        Assert.assertEquals(viewModel.validInput.value, true)
    }

    @Test
    fun `given valid pin, when next button clicked, verify go next with correct pin`() {
        // given
        val pin = "1234"
        viewModel = SetNewPinViewModel()

        // when
        viewModel.onPinChanged(pin)
        viewModel.nextButtonClicked()

        // then
        Assert.assertEquals(viewModel.goNext.value, pin)
    }
}