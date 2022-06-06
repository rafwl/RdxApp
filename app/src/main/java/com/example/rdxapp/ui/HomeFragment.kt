package com.example.rdxapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rdxapp.MainActivity
import com.example.rdxapp.R
import com.example.rdxapp.RDXApplication
import com.example.rdxapp.ui.HomeViewModel
import com.example.rdxapp.ui.HomeViewModelFactory
import com.example.rdxapp.utils.GenericSavedStateViewModelFactory
import com.example.rdxapp.utils.setupToolbarWithTitle
import com.example.rdxapp.utils.showRdxEntryDialog
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: HomeViewModelFactory

    private val viewModel: HomeViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this, arguments)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as RDXApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarWithTitle(toolbar, getString(R.string.home_toolbar_title))

        viewModel.userName.observe(viewLifecycleOwner) {
            homeTitleTextView.text = getString(R.string.home_greetings, "${it.first} ${it.second}")
        }

        viewModel.email.observe(viewLifecycleOwner) { email ->
            homeEmailTextView.text = getString(R.string.home_email_text, email)
        }

        viewModel.phoneNumber.observe(viewLifecycleOwner) { phoneNumber ->
            homePhoneNumberTextView.text = getString(R.string.home_phone_number_text, phoneNumber)
        }

        viewModel.logout.observe(viewLifecycleOwner) {
            requireActivity().finishAffinity()
            startActivity(MainActivity.newIntent(requireContext()))
        }

        viewModel.unlockPrompt.observe(viewLifecycleOwner) {
            requireActivity().showRdxEntryDialog(
                title = getString(R.string.enter_pin_to_unlock_app),
                ok = Pair(getString(R.string.ok)) {
                    viewModel.checkPin(it)
                }, cancel = Pair(getString(R.string.cancel)) {
                    requireActivity().finish()
                }
            )
        }

        viewModel.pinIncorrect.observe(viewLifecycleOwner) { pinIncorrect ->
            if (pinIncorrect) {
                requireActivity().finish()
            }
        }

        logoutButton.setOnClickListener {
            viewModel.logoutClicked()
        }
    }

    companion object {
        const val SIGN_UP_FLOW = "sign_up_flow"
    }
}