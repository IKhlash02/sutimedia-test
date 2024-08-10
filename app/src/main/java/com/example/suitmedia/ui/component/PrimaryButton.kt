package com.example.suitmedia.ui.component

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.suitmedia.ui.theme.PoppinsFontFamily

@Composable
fun PrimaryButton(
    buttonText: String,
    onButtonClick: () -> Unit, // Fungsi yang dipanggil saat button diklik
    buttonColor: Color = Color(0xFF2B637B), // Warna default button
    textColor: Color = Color.White, // Warna default teks
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    fontFamily: FontFamily = PoppinsFontFamily // Pastikan fontFamily ini terdefinisi di tempat lain
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 41.dp),
        onClick = onButtonClick,
        shape = shape,
        colors = ButtonDefaults.buttonColors(buttonColor)
    ) {
        Text(
            text = buttonText,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            color = textColor,
            textAlign = TextAlign.Center,
        )
    }
}
