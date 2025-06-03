package com.example.gymbro.ui.theme.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.gymbro.ui.theme.presentation.ActivityScreen
import com.example.gymbro.ui.theme.presentation.BasicDetailsScreen

import com.example.gymbro.ui.theme.presentation.FitnessGoalScreen
import com.example.gymbro.ui.theme.presentation.LoginScreen

import com.example.gymbro.ui.theme.presentation.WelcomeScreen
import com.example.gymbro.ui.theme.presentation.WorkoutTypeScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: @Composable NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Welcome) {
        composable(Routes.Welcome) {
            WelcomeScreen {
                navController.navigate(Routes.Login)
            }
        }
        composable(Routes.Login) {
            LoginScreen {
                navController.navigate(Routes.BasicDetails)
            }
        }
        composable(Routes.BasicDetails) {
            BasicDetailsScreen {
                navController.navigate(Routes.ActiviyScreen)
            }
        }
        composable(Routes.ActiviyScreen) {
            ActivityScreen(
                onPrevious = { navController.popBackStack() },
                onNext = { navController.navigate(Routes.WorkoutTypeScreen) }
            )
        }
        composable(Routes.WorkoutTypeScreen) {
            WorkoutTypeScreen(
                onPrevious = { navController.popBackStack() },
                onNext = { navController.navigate(Routes.FitnessGoalScreen) }
            )
        }
        composable(Routes.FitnessGoalScreen) {
            FitnessGoalScreen(
                onPrevious = { navController.popBackStack() },
                onNext = { navController.navigate(Routes.MainScreen) }
            )
        }
        composable(Routes.MainScreen) {
            MainScreen(navController = navController)
        }
    }
}
