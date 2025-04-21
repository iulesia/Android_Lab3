package com.example.lab3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab3.R

class ResultFragment : Fragment() {

    interface OnCancelListener {
        fun onCancel()
    }

    private var cancelListener: OnCancelListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCancelListener) {
            cancelListener = context
        } else {
            throw RuntimeException("$context must implement OnCancelListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_result, container, false)


        val resultTextView = view.findViewById<TextView>(R.id.result_text)
        val cancelButton = view.findViewById<Button>(R.id.cancel_button)


        val resultText = arguments?.getString("result_text")
        resultTextView.text = resultText


        cancelButton.setOnClickListener {
            cancelListener?.onCancel()
        }

        return view
    }

    companion object {
        fun newInstance(result: String): ResultFragment {
            val fragment = ResultFragment()
            val bundle = Bundle()
            bundle.putString("result_text", result)
            fragment.arguments = bundle
            return fragment
        }
    }
}
