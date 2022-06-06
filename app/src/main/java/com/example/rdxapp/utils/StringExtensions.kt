package com.example.rdxapp.utils

fun CharSequence.isValidEmail(): Boolean = isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()