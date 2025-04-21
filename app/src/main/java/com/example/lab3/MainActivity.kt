package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity(),
    InputFragment.OnInputConfirmedListener,
    ResultFragment.OnCancelListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, InputFragment())
            }
        }
    }

    override fun onInputConfirmed(resultText: String) {

        supportFragmentManager.commit {
            replace(R.id.fragment_container, ResultFragment.newInstance(resultText))
            addToBackStack(null)
        }


    }

    override fun onCancel() {
        supportFragmentManager.popBackStack()
    }
}
