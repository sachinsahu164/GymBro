package com.example.gymbro.ui.theme.navigation

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.navigation.NavHostController
import com.example.gymbro.R
import com.example.gymbro.ui.theme.presentation.DashboardScreen
import com.example.gymbro.ui.theme.presentation.NutritionScreen
import com.example.gymbro.ui.theme.presentation.ProfileScreen
import com.example.gymbro.ui.theme.presentation.WorkoutScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class NavItem(
    val route: String,
    @DrawableRes val iconRes: Int
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val navItemList = listOf(
        NavItem("dashboard", R.drawable.home),
        NavItem("workout", R.drawable.dumbell),
        NavItem("nutrition", R.drawable.nutrition),
        NavItem("profile", R.drawable.profile)
    )

    var selectedRoute by remember {
        mutableStateOf("dashboard")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Surface(
               // modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = Color.Black,
                tonalElevation = 4.dp
            ) {
                NavigationBar(
                    containerColor = Color.Transparent,
                ) {
                    navItemList.forEach { navItem ->
                        val isSelected = selectedRoute == navItem.route
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                selectedRoute = navItem.route
                            },
                            icon = {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.size(36.dp) // circle size thoda bada for padding
                                ) {
                                    if (!isSelected) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .background(Color.White, CircleShape)
                                        )
                                    }
                                    Image(
                                        painter = painterResource(id = navItem.iconRes),
                                        contentDescription = navItem.route,
                                        modifier = Modifier.size(24.dp),
                                        // No tint here; colors handle it
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = navItem.route.replaceFirstChar { it.uppercase() }
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Red,
                                unselectedIconColor = Color.Black, // black icon on white circle
                                selectedTextColor = Color.Red,
                                unselectedTextColor = Color.White
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            route = selectedRoute,
            navController = navController
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    route: String,
    navController: NavHostController
) {
    when (route) {
        "dashboard" -> DashboardScreen(
            userName = "Sachin",
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, d MMM")),
            navController = navController
        )
        "workout" -> WorkoutScreen()
        "nutrition" -> NutritionScreen()
        "profile" -> ProfileScreen()
    }
}
