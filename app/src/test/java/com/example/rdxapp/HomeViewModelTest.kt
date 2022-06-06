package com.example.rdxapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.rdxapp.data.ProfileRepository
import com.example.rdxapp.ui.HomeViewModel
import com.example.rdxapp.ui.onboarding.HomeFragment
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
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var profileRepository: ProfileRepository

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    lateinit var viewModel: HomeViewModel

    @Test
    fun `when empty input, when inserted, verify invalid input`() {
        // when
        whenever(savedStateHandle.get<Boolean>(HomeFragment.SIGN_UP_FLOW)).thenReturn(false)
        viewModel = HomeViewModel(profileRepository, savedStateHandle)

        // then
        Assert.assertEquals(viewModel.unlockPrompt.value, Unit)
    }

    @Test
    fun `given first last names valid, when viewmodel init, verify correct username`() {
        // given
        val firstName = "First"
        val lastName = "Last"
        whenever(profileRepository.firstName).thenReturn(firstName)
        whenever(profileRepository.lastName).thenReturn(lastName)

        // when
        viewModel = HomeViewModel(profileRepository, savedStateHandle)

        // then
        Assert.assertEquals(viewModel.userName.value, Pair(firstName, lastName))
    }

    @Test
    fun `given email valid, when viewmodel init, verify correct email`() {
        // given
        val email = "email@test.com"
        whenever(profileRepository.email).thenReturn(email)

        // when
        viewModel = HomeViewModel(profileRepository, savedStateHandle)

        // then
        Assert.assertEquals(viewModel.email.value, email)
    }

    @Test
    fun `given phone number valid, when viewmodel init, verify correct phone number`() {
        // given
        val phoneNumber = "1234567"
        whenever(profileRepository.phoneNumber).thenReturn(phoneNumber)

        // when
        viewModel = HomeViewModel(profileRepository, savedStateHandle)

        // then
        Assert.assertEquals(viewModel.phoneNumber.value, phoneNumber)
    }

    @Test
    fun `when pin for checkout is correct, verify correct pin`() {
        // given
        val pin = "1234"
        whenever(profileRepository.pin).thenReturn(pin)
        viewModel = HomeViewModel(profileRepository, savedStateHandle)

        // when
        viewModel.checkPin(pin)

        // then
        Assert.assertEquals(viewModel.pinIncorrect.value, false)
    }

    @Test
    fun `when pin for checkout is different, verify incorrect pin`() {
        // given
        val pin = "1234"
        val checkPin = "1122"
        whenever(profileRepository.pin).thenReturn(pin)
        viewModel = HomeViewModel(profileRepository, savedStateHandle)

        // when
        viewModel.checkPin(checkPin)

        // then
        Assert.assertEquals(viewModel.pinIncorrect.value, true)
    }

    @Test
    fun `when logout button clicked, verify logout action done`() {
        // given
        viewModel = HomeViewModel(profileRepository, savedStateHandle)

        // when
        viewModel.logoutClicked()

        // then
        Assert.assertEquals(viewModel.logout.value, Unit)
    }
}