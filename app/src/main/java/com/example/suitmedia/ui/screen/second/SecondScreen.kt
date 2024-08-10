package com.example.suitmedia.ui.screen.second

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.suitmedia.ui.component.PrimaryButton
import com.example.suitmedia.ui.theme.PoppinsFontFamily

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    name: String,
    userName: String?,
    navigateToThird: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(24.dp),


        ) {
        Text(
            text = "Welcome",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = PoppinsFontFamily
        )
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = PoppinsFontFamily
        )

        Box(
            modifier = Modifier.weight(1F).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userName ?: "Selected User Name",
                fontSize = 24.sp,
                lineHeight = 36.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF04021D),
                textAlign = TextAlign.Center,

                )
        }

        PrimaryButton(
            buttonText = "Choose a User",
            onButtonClick = {
                navigateToThird(name)
            }
        )
    }
}