package com.example.a2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Runtime & State
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

// UI Core & Layouts
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Foundation & Containers
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

// Material 3
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import com.example.a2.ui.theme._2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "start",
        modifier = modifier
    ) {
        composable("start") {
            StartScreen(
                onNavigateNext = { animal -> navController.navigate("game/$animal") }
            )
        }
        composable("game/{animal}") { backStackEntry ->
            val animal = backStackEntry.arguments?.getString("animal") ?: "cat"
            GameScreen(
                animal = animal,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onNavigateNext: (String) -> Unit = {}
) {
    var selectedOption by remember { mutableStateOf("cat") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.cat01),
                    contentDescription = "cat",
                    modifier = Modifier.size(120.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "cat",
                        onClick = { selectedOption = "cat" }
                    )
                    Text(text = "CAT")
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.dog01),
                    contentDescription = "dog",
                    modifier = Modifier.size(120.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "dog",
                        onClick = { selectedOption = "dog" }
                    )
                    Text(text = "DOG")
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { onNavigateNext(selectedOption) }
        ) {
            Text("Start")
        }
    }
}

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    animal: String = "cat",
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val imageSize = 280.dp


    val spots = if (animal == "cat") {
        listOf(0.81f to 0.12f, 0.50f to 0.25f, 0.51f to 0.43f, 0.20f to 0.67f, 0.88f to 0.83f)
    } else {
        listOf(0.16f to 0.47f, 0.30f to 0.47f, 0.44f to 0.37f, 0.87f to 0.42f, 0.41f to 0.77f)
    }
    val found = remember { mutableStateListOf<Int>() }

    val image1 = if (animal == "cat") R.drawable.cat01 else R.drawable.dog01
    val image2 = if (animal == "cat") R.drawable.cat02 else R.drawable.dog02

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(imageSize)
                .clickable {
                    Toast.makeText(context, "틀렸습니다", Toast.LENGTH_SHORT).show()
                }
        ) {
            Image(
                painter = painterResource(id = image1),
                contentDescription = animal,
                modifier = Modifier.fillMaxSize()
            )
            spots.forEachIndexed { index, spot ->
                Box(
                    modifier = Modifier
                        .offset(x = imageSize * spot.first - 20.dp, y = imageSize * spot.second - 20.dp)
                        .size(40.dp)
                        .border(
                            width = 3.dp,
                            color = if (index in found) Color.Red else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable {
                            if (index !in found) {
                                found.add(index)
                                val message = if (found.size == 5) "5개 모두 선택했습니다" else "맞았습니다"
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = image2),
            contentDescription = animal,
            modifier = Modifier.size(imageSize)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Restart")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    _2Theme {
        StartScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    _2Theme {
        GameScreen()
    }
}
