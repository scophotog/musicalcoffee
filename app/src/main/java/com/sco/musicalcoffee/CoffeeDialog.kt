package com.sco.musicalcoffee

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment

class CoffeeDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_dialog, container, false)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*
        Dialog animations aren't controlled by navigation
        You must create a style for the dialog and apply it.
        Default android theme/style fades in and out dialogs
         */
        dialog.window?.attributes?.windowAnimations = R.style.UpDownDialog
        return dialog
    }
}