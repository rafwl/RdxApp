package com.example.rdxapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rdxapp.R
import com.example.rdxapp.utils.setupToolbarUpWithTitle
import kotlinx.android.synthetic.main.fragment_terms_of_service.*

class TermsOfServiceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_terms_of_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarUpWithTitle(toolbar, true, getString(R.string.tcs_toolbar_title))

        termsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            nextButton.isEnabled = isChecked
        }

        nextButton.setOnClickListener {
            findNavController().navigate(R.id.actionCredentials)
        }
    }
}