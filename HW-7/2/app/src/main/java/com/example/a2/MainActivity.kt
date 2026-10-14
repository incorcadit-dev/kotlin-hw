package com.example.a2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a2.ui.theme._2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _2Theme {
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
    var checkedStateFireTruck by remember { mutableStateOf(false) }
    var checkedStateBlender by remember { mutableStateOf(false) }
    var checkedStateScope by remember { mutableStateOf(false) }
    var checkedStateGloves by remember { mutableStateOf(false) }

    var isCorrect = checkedStateFireTruck && !checkedStateBlender && !checkedStateScope && checkedStateGloves

    val imageSize = 80.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp).background(Color.White)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedStateFireTruck,
                        onCheckedChange = { checkedStateFireTruck = it }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img1),
                        contentDescription = "FireTruck Picture",
                        modifier = Modifier.size(imageSize),
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedStateBlender,
                        onCheckedChange = { checkedStateBlender = it }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img2),
                        contentDescription = "Blender Picture",
                        modifier = Modifier.size(imageSize),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.firefighter),
                    contentDescription = "FireFighter Picture",

                    contentScale = ContentScale.Fit
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedStateScope,
                        onCheckedChange = { checkedStateScope = it }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img3),
                        contentDescription = "Statoscope Picture",
                        modifier = Modifier.size(imageSize),
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedStateGloves,
                        onCheckedChange = { checkedStateGloves = it }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img4),
                        contentDescription = "Gloves Picture ",
                        modifier = Modifier.size(imageSize),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        Button(onClick = {
            if (isCorrect) {
                Toast.makeText(context, "정답입니다!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "오답입니다!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "결과보기")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    _2Theme {
        MainScreen()
    }
}
