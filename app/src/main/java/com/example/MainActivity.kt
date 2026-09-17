package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.local.AppDatabase
import com.example.data.local.UserRepository
import com.example.data.model.CategoryItem
import com.example.data.model.StudentRightsRepository
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.RightsListScreen
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.KnowYourRightsTheme
import com.example.ui.viewmodel.AuthViewModel
import com.example.ui.viewmodel.AuthViewModelFactory

sealed interface AppNavScreen {
    data object Login : AppNavScreen
    data object Home : AppNavScreen
    data class RightsList(val category: CategoryItem) : AppNavScreen
}

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels {
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = UserRepository(database.userDao())
        AuthViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KnowYourRightsTheme {
                MainAppNavHost(authViewModel = authViewModel)
            }
        }
    }
}

@Composable
fun MainAppNavHost(
    authViewModel: AuthViewModel
) {
    var currentScreen by remember { mutableStateOf<AppNavScreen>(AppNavScreen.Login) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        containerColor = DarkBackground
    ) { innerPadding ->
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            label = "screenTransition"
        ) { screen ->
            when (screen) {
                is AppNavScreen.Login -> {
                    LoginScreen(
                        authViewModel = authViewModel,
                        onNavigateToHome = {
                            currentScreen = AppNavScreen.Home
                        }
                    )
                }

                is AppNavScreen.Home -> {
                    HomeScreen(
                        authViewModel = authViewModel,
                        onCategorySelected = { selectedCategory ->
                            currentScreen = AppNavScreen.RightsList(selectedCategory)
                        },
                        onLogout = {
                            currentScreen = AppNavScreen.Login
                        }
                    )
                }

                is AppNavScreen.RightsList -> {
                    RightsListScreen(
                        category = screen.category,
                        onBack = {
                            currentScreen = AppNavScreen.Home
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun HomeScreenPreview() {
    KnowYourRightsTheme {
        HomeScreen(
            authViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
            onCategorySelected = {},
            onLogout = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun RightsListScreenPreview() {
    KnowYourRightsTheme {
        RightsListScreen(
            category = StudentRightsRepository.categories.first(),
            onBack = {}
        )
    }
}
