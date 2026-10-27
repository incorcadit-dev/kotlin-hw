package com.example.a3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a3.ui.theme._3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var shape by remember { mutableStateOf(1) }
    val resultImages = remember { mutableStateListOf<Int>() }
    var resultText by remember { mutableStateOf("") }

    val drawableId = listOf(
        R.drawable.shape01, R.drawable.shape02, R.drawable.shape03,
        R.drawable.shape04, R.drawable.shape05, R.drawable.shape06,
        R.drawable.shape07, R.drawable.shape08, R.drawable.shape09
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val answer = result.data?.getIntArrayExtra("answer")
            if (answer != null) {
                resultImages.clear()
                var flag = true
                for (i in 0..8) {
                    if (answer[i] == 1) {
                        resultImages.add(drawableId[i])
                        if (shape == 1 && i > 2) {
                            flag = false
                        }
                        if (shape == 2 && (i < 3 || i > 5)) {
                            flag = false
                        }
                    }
                }
                if (flag == true) {
                    resultText = "맞았습니다"
                } else {
                    resultText = "틀렸습니다"
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "ExplicitIntent 실습",
            color = Color(0xFF8BC34A),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "모양을 선택하세요",
            color = Color(0xFF9C27B0),
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = "circle",
                modifier = Modifier.size(140.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.square),
                contentDescription = "square",
                modifier = Modifier.size(140.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = shape == 1,
                    onClick = { shape = 1 }
                )
                Text(text = "CIRCLE", color = Color(0xFF3F51B5), fontWeight = FontWeight.Bold)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = shape == 2,
                    onClick = { shape = 2 }
                )
                Text(text = "SQUARE", color = Color(0xFFE53935), fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (resultText != "") {
            Text(
                text = resultText,
                color = Color(0xFFE53935),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFF80DEEA))
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (img in resultImages) {
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = "result",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
        Button(
            onClick = {
                val intent = Intent(context, GameActivity::class.java)
                intent.putExtra("shape", shape)
                launcher.launch(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00ACC1))
        ) {
            Text(text = "시작하기", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    _3Theme {
        MainScreen()
    }
}
