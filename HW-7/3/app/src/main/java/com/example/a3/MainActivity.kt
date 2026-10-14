package com.example.a3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.a3.ui.theme._3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val correctAnswer = 2

    fun checkAnswer(option: Int) {
        selectedOption = option
        val message = if (option == correctAnswer) "정답입니다!" else "틀렸습니다!"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "cat"
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Вариант 1
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = (selectedOption == 1),
                    onClick = { checkAnswer(1) }
                )
                Image(
                    painter = painterResource(id = R.drawable.cat1),
                    contentDescription = "Shadow Cat 1"
                )
            }

            // Вариант 2 (Правильный)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = (selectedOption == 2),
                    onClick = { checkAnswer(2) }
                )
                Image(
                    painter = painterResource(id = R.drawable.cat2),
                    contentDescription = "Shadow Cat 2"
                )
            }

            // Вариант 3
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = (selectedOption == 3),
                    onClick = { checkAnswer(3) }
                )
                Image(
                    painter = painterResource(id = R.drawable.cat3),
                    contentDescription = "Shadow Cat 3"
                )
            }

            // Вариант 4
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = (selectedOption == 4),
                    onClick = { checkAnswer(4) }
                )
                Image(
                    painter = painterResource(id = R.drawable.cat4),
                    contentDescription = "Shadow Cat 4"
                )
            }
        }
    }
}