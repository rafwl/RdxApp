package com.example.rdxapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rdxapp.R
import com.example.rdxapp.RDXApplication
import com.example.rdxapp.utils.ViewModelFactory
import com.example.rdxapp.utils.onChange
import com.example.rdxapp.utils.setupToolbarUpWithTitle
import kotlinx.android.synthetic.main.fragment_credentials.*
import javax.inject.Inject

class CredentialsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CredentialsViewModel>

    private lateinit var viewModel: CredentialsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as RDXApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_credentials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarUpWithTitle(toolbar, true, getString(R.string.credentials_toolbar_title))

        viewModel = ViewModelProvider(this, viewModelFactory).get(CredentialsViewModel::class.java)

        viewModel.validInput.observe(viewLifecycleOwner) { validInput ->
            nextButton.isEnabled = validInput
        }

        viewModel.goNext.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.actionPersonalInfo)
        }

        emailText.onChange {
            viewModel.onEmailChanged(it)
        }

        passwordText.onChange {
            viewModel.onPasswordChanged(it)
        }

        nextButton.setOnClickListener {
            viewModel.nextButtonClicked()
        }
    }
}