package com.example.ex4_4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etobjA: EditText
    private lateinit var etobjB: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etobjA = findViewById(R.id.etobjA)
        etobjB = findViewById(R.id.etobjB)

        val btnResult = findViewById<Button>(R.id.btnResult)
        btnResult.setOnClickListener {
            onClickChoice(it)
        }
    }

    private fun onClickChoice(view: android.view.View?) {
        val partA = etobjA.text.toString().toIntOrNull()
        val partB = etobjB.text.toString().toIntOrNull()

        if (partA == 3 && partB == 3) {
            Toast.makeText(this, "맞았습니다", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "틀렸습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
