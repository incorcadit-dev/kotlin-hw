package com.example.ex4_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val diceNumber = intArrayOf(
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6
    )

    private var numA = 0
    private var numB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivDice1 = findViewById<ImageView>(R.id.ivDice1)
        val ivDice2 = findViewById<ImageView>(R.id.ivDice2)
        val etSum = findViewById<EditText>(R.id.etSum)
        val btnResult = findViewById<Button>(R.id.btnResult)

        numA = (1..6).random()
        numB = (1..6).random()

        ivDice1.setImageResource(diceNumber[numA - 1])
        ivDice2.setImageResource(diceNumber[numB - 1])

        btnResult.setOnClickListener {
            val userSum = etSum.text.toString().toIntOrNull()
            if (userSum != null && userSum == numA + numB) {
                Toast.makeText(this, "맞았습니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "틀렸습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
