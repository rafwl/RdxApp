package com.example.rdxapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.setupToolbarUpWithTitle(toolbar: Toolbar, titleEnabled: Boolean, title: String = "") {
    toolbar.title = title
    setupToolbarWithUpButton(toolbar, titleEnabled)
}

fun Fragment.setupToolbarWithUpButton(toolbar: Toolbar, titleEnabled: Boolean) {
    (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
    (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)
    (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(titleEnabled)
}

fun Fragment.setupToolbarWithTitle(toolbar: Toolbar, title: String) {
    toolbar.title = title
    (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
    (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(true)
}