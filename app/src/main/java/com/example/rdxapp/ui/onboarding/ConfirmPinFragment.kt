package com.example.rdxapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rdxapp.R
import com.example.rdxapp.RDXApplication
import com.example.rdxapp.ui.onboarding.HomeFragment.Companion.SIGN_UP_FLOW
import com.example.rdxapp.utils.GenericSavedStateViewModelFactory
import com.example.rdxapp.utils.onChange
import com.example.rdxapp.utils.setupToolbarUpWithTitle
import com.example.rdxapp.utils.showRdxDialog
import kotlinx.android.synthetic.main.fragment_set_new_pin.*
import javax.inject.Inject

class ConfirmPinFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ConfirmPinViewModelFactory

    private val viewModel: ConfirmPinViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this, requireArguments())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as RDXApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_set_new_pin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarUpWithTitle(toolbar, true, getString(R.string.confirm_pin_toolbar_title))
        pinText.setHint(R.string.confirm_pin_hint)

        pinText.onChange {
            viewModel.onPinChanged(it)
        }

        viewModel.goNext.observe(viewLifecycleOwner) {
            val bundle = bundleOf(
                SIGN_UP_FLOW to true
            )
            findNavController().navigate(R.id.actionHome, bundle)
        }

        viewModel.validInput.observe(viewLifecycleOwner) { validInput ->
            nextButton.isEnabled = validInput
        }

        viewModel.pinsDifferent.observe(viewLifecycleOwner) {
            requireContext().showRdxDialog(
                getString(R.string.error),
                getString(R.string.pins_different_error_body),
                ok = Pair(getString(R.string.ok)){
                    requireActivity().onBackPressed()
                }
            )
        }

        nextButton.setOnClickListener {
            viewModel.nextButtonClicked()
        }
    }

    companion object {
        const val PIN = "pin"
    }
}