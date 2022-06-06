package com.example.rdxapp.utils

import android.content.Context
import android.text.InputType
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showRdxDialog(
    title: String = "",
    message: String = "",
    ok: Pair<String,()->Unit>,
    cancel: Pair<String,()->Unit>? = null){

    val builder = MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(ok.first) { _,_ -> ok.second() }

    cancel?.let{
        builder.setNegativeButton(it.first) { _, _ -> it.second() }
    }

    builder.create().show()
}

fun Context.showRdxEntryDialog(
    title: String = "",
    ok: Pair<String, (entry: String)->Unit>,
    cancel: Pair<String, ()->Unit>? = null){

    val editText = EditText(this)
    editText.inputType = InputType.TYPE_CLASS_NUMBER or
            InputType.TYPE_NUMBER_FLAG_DECIMAL or
            InputType.TYPE_NUMBER_FLAG_SIGNED
    val builder = MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setView(editText)
        .setCancelable(false)
        .setPositiveButton(ok.first) { _,_ -> ok.second(editText.text.toString()) }

    cancel?.let{
        builder.setNegativeButton(it.first) { _, _ -> it.second() }
    }

    builder.create().show()
}