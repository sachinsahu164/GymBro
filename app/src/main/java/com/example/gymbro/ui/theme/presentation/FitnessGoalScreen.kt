package com.example.gymbro.ui.theme.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymbro.R

class FitnessGoalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessGoalScreen(
                onNext = {
                    // Navigate to next screen
                },
                onPrevious = {
                    // Go back
                }
            )
        }
    }
}

@Composable
fun FitnessGoalScreen(
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    var selectedGoal by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.fitnessbg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xAA000000), Color(0x55000000))
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What is your primary\nfitness goal?",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            GoalOption("Muscle Gain", R.drawable.dumbell, selectedGoal) {
                selectedGoal = "Muscle Gain"
            }
            Spacer(modifier = Modifier.height(12.dp))

            GoalOption("Fat Loss", R.drawable.person, selectedGoal) {
                selectedGoal = "Fat Loss"
            }
            Spacer(modifier = Modifier.height(12.dp))

            GoalOption("General Fitness", R.drawable.heart, selectedGoal) {
                selectedGoal = "General Fitness"
            }
            Spacer(modifier = Modifier.height(12.dp))

            GoalOption("Skip for Now...", null, selectedGoal) {
                selectedGoal = "Skip for Now..."
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Navigation Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NavButton("Back", R.drawable.previous, onPrevious)
                NavButton("Next", R.drawable.forward, onNext)
            }
        }
    }
}

@Composable
fun GoalOption(text: String, iconRes: Int?, selectedGoal: String?, onClick: () -> Unit) {
    val isSelected = text == selectedGoal

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color.White.copy(alpha = 0.2f) else Color.Black.copy(alpha = 0.6f),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() }
            .then(
                if (isSelected) Modifier.border(2.dp, Color.White, RoundedCornerShape(16.dp))
                else Modifier
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            if (iconRes != null) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = text,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun NavButton(text: String, iconRes: Int, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color.LightGray.copy(alpha = 0.9f),
        modifier = Modifier
            .width(130.dp)
            .height(40.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = text, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessGoalScreenPreview() {
    FitnessGoalScreen(
        onNext = {},
        onPrevious = {}
    )
}