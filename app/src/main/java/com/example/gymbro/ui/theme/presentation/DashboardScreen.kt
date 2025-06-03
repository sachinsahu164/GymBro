package com.example.gymbro.ui.theme.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymbro.R
//import com.example.gymbro.ui.theme.navigation.BottomNavBar
import com.example.gymbro.ui.theme.navigation.Routes
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DashboardScreen(
    userName: String,
    date: String,
    navController: NavHostController
) {
    var selectedTab by remember { mutableStateOf("Home") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("GYM BRO", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Red)
                Text("Hello, $userName", fontSize = 16.sp, color = Color.White)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("unleash your inner beast !", fontSize = 12.sp, color = Color.White)
                Text(date, color = Color.White, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TodayWorkoutCard(
                imageRes = R.drawable.todayworkout,
                title = "Start Today",
                subtitle = "Workout"
            )

            CardItem(
                imageRes = R.drawable.schedule,
                title = "Workout",
                subtitle = "Schedule",
                bgColor = Color.Black,
                textColor = Color.White
            )

            Button(
                onClick = {},
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text("Unlock beast mode", fontWeight = FontWeight.Bold, color = Color.Black)
            }

            CardItem(
                imageRes = R.drawable.yogatab,
                title = "Yoga",
                subtitle = "The Power Within You",
                bgColor = Color(0xFF9CCC65),
                textColor = Color.White
            )

            CardItem(
                imageRes = R.drawable.zumba,
                title = "Zumba",
                subtitle = "Fun Cardio",
                bgColor = Color(0xFF8BC34A),
                textColor = Color.Black
            )

            CardItem(
                imageRes = R.drawable.calisthenics,
                title = "Calisthenics",
                subtitle = "Control Mastered",
                bgColor = Color(0xFF81D4FA),
                textColor = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))
        }


    }
}



@Composable
fun TodayWorkoutCard(imageRes: Int, title: String, subtitle: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(30.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.End) {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = subtitle, fontSize = 18.sp, color = Color.Black)
            }
        }

        Image(
            painter = painterResource(id = R.drawable.unlock),
            contentDescription = "lock",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .graphicsLayer(alpha = 0.6f)
        )
    }
}

@Composable
fun CardItem(imageRes: Int, title: String, subtitle: String, bgColor: Color, textColor: Color = Color.Black) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(vertical = 8.dp)
            .background(bgColor, RoundedCornerShape(30.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(30.dp))
        )

        Image(
            painter = painterResource(id = R.drawable.unlock),
            contentDescription = "lock",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .graphicsLayer(alpha = 0.6f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor)
            Text(text = subtitle, fontSize = 18.sp, color = textColor)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    DashboardScreen(userName = "Sachin", date = "Today, 16 Mar", navController = rememberNavController())
}
