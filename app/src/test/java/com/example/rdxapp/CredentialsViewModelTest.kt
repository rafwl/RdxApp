package com.example.rdxapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.ui.onboarding.CredentialsViewModel
import com.example.rdxapp.utils.isValidEmail
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CredentialsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var profileRepository: ProfileRepository

    lateinit var viewModel: CredentialsViewModel

    @Before
    fun setUp() {
        mockkStatic("com.example.rdxapp.utils.StringExtensionsKt")
    }

    @Test
    fun `when invalid email&pass, when view data inserted, verify invalid input`() {
        // given
        val validEmail = "m"
        val password = ""
        every {
            validEmail.isValidEmail()
        } returns false
        viewModel = CredentialsViewModel(profileRepository)

        // when
        viewModel.onPasswordChanged(password)
        viewModel.onEmailChanged(validEmail)

        // then
        Assert.assertEquals(viewModel.validInput.value, false)
    }

    @Test
    fun `when valid email&pass, when view data inserted, verify valid input`() {
        // given
        val validEmail = "test@gmail.com"
        val password = "password123"
        every {
            validEmail.isValidEmail()
        } returns true
        viewModel = CredentialsViewModel(profileRepository)

        // when
        viewModel.onPasswordChanged(password)
        viewModel.onEmailChanged(validEmail)

        // then
        Assert.assertEquals(viewModel.validInput.value, true)
    }

    @Test
    fun `when invalid email, valid pass, when view data inserted, verify invalid input`() {
        // given
        val invalidEmail = "invalid_email.com"
        val password = "password123"
        every {
            invalidEmail.isValidEmail()
        } returns false
        viewModel = CredentialsViewModel(profileRepository)

        // when
        viewModel.onPasswordChanged(password)
        viewModel.onEmailChanged(invalidEmail)

        // then
        Assert.assertEquals(viewModel.validInput.value, false)
    }

    @Test
    fun `when valid email&pass inserted, when next button clicked, verify go next`() {
        // given
        val validEmail = "test@gmail.com"
        val password = "password123"
        every {
            validEmail.isValidEmail()
        } returns true
        viewModel = CredentialsViewModel(profileRepository)

        // when
        viewModel.onPasswordChanged(password)
        viewModel.onEmailChanged(validEmail)
        viewModel.nextButtonClicked()

        // then
        Assert.assertEquals(viewModel.goNext.value, Unit)
    }
}