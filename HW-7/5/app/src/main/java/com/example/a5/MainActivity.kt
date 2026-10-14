package com.example.a5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a5.ui.theme._5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var isCatVisible by remember { mutableStateOf(true) }
    var isLightOn by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.window),
                contentDescription = "window",
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(if (isLightOn) R.drawable.lighton else R.drawable.lightoff),
                contentDescription = "light",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 105.dp)
            )

            if (isCatVisible) {
                Image(
                    painter = painterResource(R.drawable.cat5),
                    contentDescription = "cat",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 90.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "고양이", fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
                Switch(
                    checked = isCatVisible,
                    onCheckedChange = { isCatVisible = it }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "라이트", fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
                Switch(
                    checked = isLightOn,
                    onCheckedChange = { isLightOn = it }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    _5Theme {
        MainScreen()
    }
}