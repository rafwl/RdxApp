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
import com.example.rdxapp.ui.onboarding.ConfirmPinFragment.Companion.PIN
import com.example.rdxapp.utils.onChange
import com.example.rdxapp.utils.setupToolbarUpWithTitle
import kotlinx.android.synthetic.main.fragment_set_new_pin.*

class SetNewPinFragment : Fragment() {

    private val viewModel: SetNewPinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_set_new_pin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarUpWithTitle(toolbar, true, getString(R.string.set_pin_toolbar_title))
        pinText.setHint(R.string.set_pin_hint)

        viewModel.goNext.observe(viewLifecycleOwner) { pin ->
            val bundle = bundleOf(
                PIN to pin
            )
            findNavController().navigate(R.id.actionConfirmPin, bundle)
        }

        viewModel.validInput.observe(viewLifecycleOwner) { validInput ->
            nextButton.isEnabled = validInput
        }

        pinText.onChange {
            viewModel.onPinChanged(it)
        }

        nextButton.setOnClickListener {
            viewModel.nextButtonClicked()
        }
    }
}