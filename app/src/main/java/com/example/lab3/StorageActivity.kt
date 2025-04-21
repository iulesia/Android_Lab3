package com.example.lab3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class StorageActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        val storageText = findViewById<TextView>(R.id.tvStorage)

        try {
            val fileInput = openFileInput("saved_data.txt")
            val content = fileInput.bufferedReader().use { it.readText() }

            if (content.isBlank()) {
                storageText.text = "There are no data"
            } else {
                storageText.text = content
            }
        } catch (e: Exception) {
            storageText.text = "File not found or empty"
        }

        val btnClear = findViewById<Button>(R.id.btnClear)
        btnClear.setOnClickListener {
            try {
                openFileOutput("saved_data.txt", MODE_PRIVATE).use {
                    it.write("".toByteArray())
                }
                storageText.text = "There are no data"
            } catch (e: Exception) {
                storageText.text = "Failed to clear file"
            }
        }
    }


}
