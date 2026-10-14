package com.example.a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.a1.ui.theme._1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var chosenAnswer by remember { mutableStateOf<Int?>(null) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.question),
                contentDescription = "Question"
            )
            chosenAnswer?.let { answerResId ->
                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 40.dp),
                    painter = painterResource(id = answerResId),
                    contentDescription = null
                )
            }
        }
        Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { chosenAnswer = R.drawable.answer1 },
                modifier = Modifier.size(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.answer1),
                    contentDescription = "Answer 1",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Button(
                onClick = { chosenAnswer = R.drawable.answer2 },
                modifier = Modifier.size(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.answer2),
                    contentDescription = "Answer 2",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Button(
                onClick = { chosenAnswer = R.drawable.answer3 },
                modifier = Modifier.size(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.answer3),
                    contentDescription = "Answer 3",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Button(
                onClick = { chosenAnswer = R.drawable.answer4 },
                modifier = Modifier.size(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.answer4),
                    contentDescription = "Answer 4",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
