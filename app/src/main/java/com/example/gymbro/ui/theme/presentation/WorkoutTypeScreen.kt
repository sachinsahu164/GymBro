package com.example.gymbro.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymbro.R

@Composable
fun WorkoutTypeScreen(
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    val options = listOf(
        "Gym Workout",
        "Home Workout",
        "Yoga",
        "Other"
    )

    var selectedOption by remember { mutableStateOf(options[0]) }

    Box(modifier = Modifier.fillMaxSize()) {
        // ✅ Full screen background image with ContentScale.Crop
        Image(
            painter = painterResource(id = R.drawable.workoutbg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ✅ White transparent box with rounded corners
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "What’s your preferred\nworkout type?",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            options.forEach { option ->
                Button(
                    onClick = { selectedOption = option },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(option)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = onPrevious) { Text("Previous") }
                Button(onClick = onNext) { Text("Next") }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WorkoutTypeScreenPreview() {
    WorkoutTypeScreen(
        onPrevious = { /* Navigation logic here */ },
        onNext = { /* Navigation logic here */ }
    )
}
