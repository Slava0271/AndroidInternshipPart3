package com.example.androidinternshippart3.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.DialogFragment
import kotlin.system.exitProcess

class Dialog(private val message: String) : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                    .setPositiveButton("Ок",
                            DialogInterface.OnClickListener { _, _ ->
                                // FIRE ZE MISSILES!
                            })
                    .setNegativeButton("Выход",
                            DialogInterface.OnClickListener { _, _ ->
                                exitProcess(0)
                            })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}