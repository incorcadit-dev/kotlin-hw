package com.example.ex4_2

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLess = findViewById<ImageButton>(R.id.btnLess)
        val btnGreater = findViewById<ImageButton>(R.id.btnGreater)
        val btnEqual = findViewById<ImageButton>(R.id.btnEqual)

        btnLess.setOnClickListener {
            Toast.makeText(this, "맞았습니다", Toast.LENGTH_SHORT).show()
        }

        btnGreater.setOnClickListener {
            Toast.makeText(this, "틀렸습니다", Toast.LENGTH_SHORT).show()
        }

        btnEqual.setOnClickListener {
            Toast.makeText(this, "틀렸습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
