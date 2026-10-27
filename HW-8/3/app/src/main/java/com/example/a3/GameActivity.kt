package com.example.a3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a3.ui.theme._3Theme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val shape = intent.getIntExtra("shape", 0)
        setContent {
            _3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(
                        shape = shape,
                        onResult = { answer ->
                            val resultIntent = Intent()
                            resultIntent.putExtra("answer", answer)
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameScreen(shape: Int, onResult: (IntArray) -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val drawableId = listOf(
        R.drawable.shape01, R.drawable.shape02, R.drawable.shape03,
        R.drawable.shape04, R.drawable.shape05, R.drawable.shape06,
        R.drawable.shape07, R.drawable.shape08, R.drawable.shape09
    )
    val order = listOf(7, 3, 2, 1, 8, 5, 6, 0, 4)

    val selectNum = remember { mutableStateListOf(0, 0, 0, 0, 0, 0, 0, 0, 0) }
    var num by remember { mutableStateOf(0) }

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            if (shape == 1) {
                Image(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "circle",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.square),
                    contentDescription = "square",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        for (row in 0..2) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (col in 0..2) {
                    val i = order[row * 3 + col]
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clickable {
                                if (selectNum[i] == 1) {
                                    selectNum[i] = 0
                                    num--
                                } else {
                                    if (num < 3) {
                                        selectNum[i] = 1
                                        num++
                                    } else {
                                        Toast.makeText(context, "3개 까지만 선택 가능합니다", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                    ) {
                        Image(
                            painter = painterResource(id = drawableId[i]),
                            contentDescription = "shape",
                            modifier = Modifier.fillMaxSize()
                        )
                        if (selectNum[i] == 1) {
                            Image(
                                painter = painterResource(id = R.drawable.ok_check),
                                contentDescription = "check",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
        Button(
            onClick = { onResult(selectNum.toIntArray()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00ACC1))
        ) {
            Text(text = "결과보기", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    _3Theme {
        GameScreen(shape = 1, onResult = {})
    }
}
