package com.example.suitmedia

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.suitmedia.ui.navigation.Screen
import com.example.suitmedia.ui.screen.first.FirstScreen
import com.example.suitmedia.ui.screen.second.SecondScreen
import com.example.suitmedia.ui.screen.third.ThirdScreen
import com.example.suitmedia.ui.theme.PoppinsFontFamily

@Composable
fun SuitMediaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    fun NavBackStackEntry?.fromRoute(): Screen {
        this?.destination?.route?.substringBefore("?")?.substringBefore("/")
            ?.substringAfterLast(".")?.let {
                when (it) {
                    Screen.First::class.simpleName -> return Screen.First
                    Screen.Second::class.simpleName -> return Screen.Second(name = null, userName = null)
                    Screen.Third::class.simpleName -> return  Screen.Third(name = null)
                    else -> return Screen.First
                }
            }
        return Screen.First
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry.fromRoute()

    Scaffold(
        topBar = {
            if (currentRoute !is Screen.First) {

                MyTopBar(
                    title = if (currentRoute is Screen.Second) "Second Screen" else "Third Screen",
                    navController = navController
                )
            }

        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.First,
            modifier = modifier.padding(innerPadding)
        ) {
            composable<Screen.First> {
                FirstScreen(
                    navigateToSecond = { name ->
                        navController.navigate(Screen.Second(name = name, userName = null))
                    }
                )
            }
            composable<Screen.Second> {
                val arg = it.toRoute<Screen.Second>()
                SecondScreen(
                    name = arg.name ?: "",
                    userName = arg.userName,
                    navigateToThird = { name ->
                        navController.navigate(Screen.Third(name = name))
                    })
            }
            composable<Screen.Third> {
                val arg = it.toRoute<Screen.Third>()
                ThirdScreen(
                    navigateToSecondScreen = { username ->
                        navController.navigate(
                            Screen.Second(
                                name = arg.name,
                                userName = username
                            )
                        ) {
                            popUpTo<Screen.Second> { inclusive = true }
                        }
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String,
    navController: NavHostController,
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(elevation = 1.dp, shape = RectangleShape),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Menu",
                    tint = Color(0xff554AF0),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                lineHeight = 27.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF04021D),
                textAlign = TextAlign.Center,
            )
        },


        )
}