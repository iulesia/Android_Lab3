package com.example.lab3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class ViewDataActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        val tvData = findViewById<TextView>(R.id.tvData)
        val filename = "question_data.txt"

        try {
            val inputStream = openFileInput(filename)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val content = reader.readText()
            tvData.text = content
            reader.close()
        } catch (e: Exception) {
            tvData.text = "No data saved"
        }
    }
}
