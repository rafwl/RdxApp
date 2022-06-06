package com.example.rdxapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.ui.onboarding.PersonalInfoViewModel
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
class PersonalInfoViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var profileRepository: ProfileRepository

    lateinit var viewModel: PersonalInfoViewModel

    @Test
    fun `when empty input, when inserted, verify invalid input`() {
        // given
        val firstName = ""
        val lastName = ""
        val phoneNumber = ""
        viewModel = PersonalInfoViewModel(profileRepository)

        // when
        viewModel.onFirstNameChanged(firstName)
        viewModel.onLastNameChanged(lastName)
        viewModel.onPhoneNumberChanged(phoneNumber)

        // then
        Assert.assertEquals(viewModel.validInput.value, false)
    }

    @Test
    fun `when valid input, when inserted, verify valid input`() {
        // given
        val firstName = "first"
        val lastName = "last"
        val phoneNumber = "0123456"
        viewModel = PersonalInfoViewModel(profileRepository)

        // when
        viewModel.onFirstNameChanged(firstName)
        viewModel.onLastNameChanged(lastName)
        viewModel.onPhoneNumberChanged(phoneNumber)

        // then
        Assert.assertEquals(viewModel.validInput.value, true)
    }

    @Test
    fun `when valid input, when next button clicked, verify go next`() {
        // given
        viewModel = PersonalInfoViewModel(profileRepository)

        // when
        viewModel.nextButtonClicked()

        // then
        Assert.assertEquals(viewModel.goNext.value, Unit)
    }
}