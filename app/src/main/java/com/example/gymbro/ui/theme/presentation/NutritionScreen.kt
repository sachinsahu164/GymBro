package com.example.gymbro.ui.theme.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
import com.example.gymbro.R
//import com.example.gymbro.ui.theme.navigation.BottomNavBar

@Composable
fun NutritionScreen(username: String = "Sachin") {
    var breakfastMeal by remember { mutableStateOf("") }
    var lunchMeal by remember { mutableStateOf("") }
    var dinnerMeal by remember { mutableStateOf("") }
    val waterIntake = remember { mutableStateListOf(false, false, false, false, false, false, false, false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$username - nutrition plan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        MealCard("Breakfast", breakfastMeal, { breakfastMeal = it }, R.drawable.break_fast)
        MealCard("Lunch Time", lunchMeal, { lunchMeal = it }, R.drawable.lunch)
        MealCard("Dinner Meal", dinnerMeal, { dinnerMeal = it }, R.drawable.dineer)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Water Intake",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.drink_water),
                    contentDescription = "Starting Glass Icon",
                    modifier = Modifier.size(35.dp)
                )

                waterIntake.forEachIndexed { index, isDrank ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                waterIntake[index] = !waterIntake[index]
                            }
                            .background(Color.Transparent)
                            .drawBehind {
                                if (isDrank) {
                                    drawRoundRect(
                                        color = Color(0xFF4CAF50),
                                        style = Stroke(width = 4.dp.toPx()),
                                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
                                    )
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.glassicon),
                            contentDescription = "Glass $index",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Calorie Meter",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(12.dp))
                SimplePieChart(
                    modifier = Modifier.size(200.dp),
                    values = listOf(25f, 30f, 45f),
                    colors = listOf(Color(0xFF42A5F5), Color(0xFFFF7043), Color(0xFF66BB6A))
                )
            }
        }
    }
}

@Composable
fun MealCard(title: String, meal: String, onMealChange: (String) -> Unit, imageRes: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 8.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = meal,
                        onValueChange = onMealChange,
                        label = { Text("Enter your meal", color = Color.White) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            cursorColor = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SimplePieChart(modifier: Modifier = Modifier, values: List<Float>, colors: List<Color>) {
    val sum = values.sum()
    val angles = values.map { it / sum * 360f }

    Canvas(modifier = modifier) {
        var startAngle = -90f
        for (i in values.indices) {
            drawArc(
                color = colors[i],
                startAngle = startAngle,
                sweepAngle = angles[i],
                useCenter = true,
                size = Size(size.width, size.height)
            )
            startAngle += angles[i]
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutritionScreenPreview() {
    NutritionScreen()
}
