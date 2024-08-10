package com.example.suitmedia.ui.screen.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.suitmedia.R
import com.example.suitmedia.ui.component.PrimaryButton
import com.example.suitmedia.ui.theme.PoppinsFontFamily
import com.example.suitmedia.ui.theme.SuitmediaTheme


@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navigateToSecond: (String) -> Unit
) {

    var name by remember {
        mutableStateOf("")
    }

    var palindrome by remember {
        mutableStateOf("")
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var messageDialog by remember {
        mutableStateOf("")
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(79, 166, 169),  // Top Color
                        Color(107, 148, 173), // Middle Color
                        Color(64, 117, 149)
                    )
                )
            )
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "ic_photo",
            modifier = Modifier.size(116.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
        TextField(
            value = name,
            onValueChange = { newInput ->
                name = newInput

            },
            shape = RoundedCornerShape(20.dp),
            placeholder = {
                Text(
                    text = "Name",
                    fontSize = 16.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color(0x5C686777)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = palindrome,
            onValueChange = { newInput ->
                palindrome = newInput
            },
            shape = RoundedCornerShape(20.dp),
            placeholder = {
                Text(
                    text = "Palindrome",
                    fontSize = 16.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color(0x5C686777)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(40.dp))
        PrimaryButton(
            buttonText = "Check",
            onButtonClick = {
                if (palindrome.isBlank()) {
                    messageDialog = "Palindrome cannot be empty"
                    showDialog = true
                } else {
                    messageDialog =
                        if (palindrome == palindrome.reversed()) "isPalindrome" else "not palindrome"
                    showDialog = true
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        PrimaryButton(
            buttonText = "Next",
            onButtonClick = {
                if (name.isBlank()) {
                    messageDialog = "Name cannot be empty"
                    showDialog = true
                } else {
                    navigateToSecond(name)
                }
            }
        )

        if (showDialog) {
            PalindromeDialog(
                message = messageDialog,
                onDismissRequest = { showDialog = false }
            )
        }

    }


}

@Composable
fun PalindromeDialog(
    message: String,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = message,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FirstPreview() {
    SuitmediaTheme {
        FirstScreen(navigateToSecond = {})
    }
}