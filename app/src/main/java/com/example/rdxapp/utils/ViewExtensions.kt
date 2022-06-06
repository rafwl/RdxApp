package com.example.rdxapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onChange(codeBlock: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) { codeBlock(s.toString().trim()) }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}