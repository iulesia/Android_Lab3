package com.example.lab3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.io.FileOutputStream

class InputFragment : Fragment() {

    interface OnInputConfirmedListener {
        fun onInputConfirmed(resultText: String)
    }

    private var listener: OnInputConfirmedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInputConfirmedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnInputConfirmedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        val inputField = view.findViewById<EditText>(R.id.etQuestion)
        val typeGroup = view.findViewById<RadioGroup>(R.id.rgType)
        val levelGroup = view.findViewById<RadioGroup>(R.id.rgDifficulty)
        val okButton = view.findViewById<Button>(R.id.btnOk)
        val openButton = view.findViewById<Button>(R.id.btnOpen)

        okButton.setOnClickListener {
            val inputText = inputField.text.toString().trim()
            val selectedTypeId = typeGroup.checkedRadioButtonId
            val selectedLevelId = levelGroup.checkedRadioButtonId

            if (inputText.isEmpty() || selectedTypeId == -1 || selectedLevelId == -1) {
                Toast.makeText(requireContext(), "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val type = view.findViewById<RadioButton>(selectedTypeId)?.text
            val level = view.findViewById<RadioButton>(selectedLevelId)?.text

            val result = "Question: $inputText\nType: $type\nDifficulty: $level"


            try {
                requireContext().openFileOutput("saved_data.txt", Context.MODE_APPEND).use {
                    it.write((result + "\n\n").toByteArray())
                }
                Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Save error", Toast.LENGTH_SHORT).show()
            }

            listener?.onInputConfirmed(result)
        }


        openButton.setOnClickListener {
            val intent = Intent(requireContext(), StorageActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
