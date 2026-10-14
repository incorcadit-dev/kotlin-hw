package com.example.ex4_3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var ivobjCrown: ImageView
    private lateinit var ivobjNecklace: ImageView
    private lateinit var ivobjDress: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivobjCrown = findViewById(R.id.ivobjCrown)
        ivobjNecklace = findViewById(R.id.ivobjNecklace)
        ivobjDress = findViewById(R.id.ivobjDress)

        val btnReset = findViewById<Button>(R.id.btnReset)
        btnReset.setOnClickListener {
            ivobjCrown.visibility = View.INVISIBLE
            ivobjNecklace.visibility = View.INVISIBLE
            ivobjDress.visibility = View.INVISIBLE
        }

        val itemIds = intArrayOf(
            R.id.imageViewCrown1,
            R.id.imageViewCrown2,
            R.id.imageViewNecklace1,
            R.id.imageViewNecklace2,
            R.id.imageViewDress1,
            R.id.imageViewDress2,
            R.id.imageViewDress3
        )

        for (id in itemIds) {
            findViewById<View>(id)?.setOnClickListener { onClickChoice(it) }
        }
    }

    fun onClickChoice(view: View) {
        when (view.id) {
            R.id.imageViewCrown1 -> {
                ivobjCrown.visibility = View.VISIBLE
                ivobjCrown.setImageResource(R.drawable.crown1)
            }
            R.id.imageViewCrown2 -> {
                ivobjCrown.visibility = View.VISIBLE
                ivobjCrown.setImageResource(R.drawable.crown2)
            }
            R.id.imageViewNecklace1 -> {
                ivobjNecklace.visibility = View.VISIBLE
                ivobjNecklace.setImageResource(R.drawable.necklace1)
            }
            R.id.imageViewNecklace2 -> {
                ivobjNecklace.visibility = View.VISIBLE
                ivobjNecklace.setImageResource(R.drawable.necklace2)
            }
            R.id.imageViewDress1 -> {
                ivobjDress.visibility = View.VISIBLE
                ivobjDress.setImageResource(R.drawable.dress1)
            }
            R.id.imageViewDress2 -> {
                ivobjDress.visibility = View.VISIBLE
                ivobjDress.setImageResource(R.drawable.dress2)
            }
            R.id.imageViewDress3 -> {
                ivobjDress.visibility = View.VISIBLE
                ivobjDress.setImageResource(R.drawable.dress3)
            }
        }
    }
}
