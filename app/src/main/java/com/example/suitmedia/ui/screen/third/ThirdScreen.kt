package com.example.suitmedia.ui.screen.third

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.suitmedia.ViewModelFactory
import com.example.suitmedia.data.response.DataItem
import com.example.suitmedia.ui.theme.PoppinsFontFamily

@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier,
    navigateToSecondScreen: (String) -> Unit,
    thirdViewModel: ThirdViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    )
) {

    val userItems: LazyPagingItems<DataItem> = thirdViewModel.userData.collectAsLazyPagingItems()


    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {

        items(
            items = userItems
        ) { user ->
            Column(
                modifier = Modifier.clickable {
                    navigateToSecondScreen("${user?.firstName} ${user?.lastName}")
                }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    AsyncImage(
                        model = user?.avatar,
                        contentDescription = null,
                        modifier = Modifier
                            .size(49.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "${user?.firstName} ${user?.lastName}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF04021D),
                            fontFamily = PoppinsFontFamily
                        )
                        Text(
                            text = user?.email ?: "",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF686777),
                            fontFamily = PoppinsFontFamily
                        )
                    }
                }

                Divider()
            }

        }

        when (userItems.loadState.refresh) {
            is LoadState.Error -> {
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }

        when (userItems.loadState.append) {
            is LoadState.Error -> {

            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }
}
