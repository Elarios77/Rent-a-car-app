package com.example.rentacarapp.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rentacarapp.R
import com.example.rentacarapp.ui.cars.history.screen.HistoryScreen
import com.example.rentacarapp.ui.cars.main.screen.MainScreen
import com.example.rentacarapp.ui.cars.rental.screen.RentScreen
import com.example.rentacarapp.ui.navigation.LoginNavigation
import com.example.rentacarapp.ui.tabs.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch

sealed class TabItem(val title: Int, val icon: ImageVector) {
    object Main : TabItem(title = R.string.main, Icons.Default.Home)
    object Rent : TabItem(title = R.string.rent, Icons.Default.CarRental)
    object History : TabItem(title = R.string.history, Icons.Default.History)
}

@Composable
fun TabsDashboard(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    TabsDashboardContent(
        onLogoutClick = {
            viewModel.onLogoutClick {
                navController.navigate(LoginNavigation.Login.name) {
                    popUpTo(0) { inclusive = true }
                }
            }
        },
        onProfileClick = {
            navController.navigate(LoginNavigation.Profile.name)
        },
        onInfoClick = {
            navController.navigate(LoginNavigation.Info.name)
        }
    )
}

@Composable
fun TabsDashboardContent(
    onLogoutClick: () -> Unit,
    onProfileClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    val tabTitles = listOf(TabItem.Main, TabItem.Rent, TabItem.History)
    val pagerState = rememberPagerState { tabTitles.size }
    val scope = rememberCoroutineScope()

    var isProfileMenuExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {

        Surface(
            color = Color(0xFF0e72a6),
            shadowElevation = 4.dp
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                PrimaryTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.weight(1f),
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                    indicator = {},
                    divider = {
                        HorizontalDivider(
                            thickness = 3.dp,
                            color = Color(0xFF084A6E)
                        )
                    }
                ) {
                    tabTitles.forEachIndexed { index, tab ->
                        val selected = pagerState.currentPage == index
                        Tab(
                            modifier = if (selected) Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFF084A6E))
                            else Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFF0e72a6)),
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            selected = selected,
                            text = {
                                Text(
                                    text = stringResource(id = tab.title),
                                    fontSize = 12.sp
                                )
                            },
                            selectedContentColor = Color.LightGray,
                            unselectedContentColor = Color.Black,
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopEnd)
                        .background(color = colorResource(R.color.darker_mainColor))
                ) {
                    IconButton(onClick = { isProfileMenuExpanded = true })
                    {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = isProfileMenuExpanded,
                        onDismissRequest = { isProfileMenuExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = stringResource(R.string.myprofile)) },
                            onClick = {
                                isProfileMenuExpanded = false
                                onProfileClick()
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = null
                                )
                            }
                        )

                        HorizontalDivider()
                        DropdownMenuItem(
                            text = { Text(text = stringResource(R.string.info)) },
                            onClick = {
                                isProfileMenuExpanded = false
                                onInfoClick()
                            }
                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            text = { Text(text = stringResource(R.string.logout)) },
                            onClick = {
                                isProfileMenuExpanded = false
                                onLogoutClick()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }


        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) { pagerIndex ->
            when (pagerIndex) {
                0 -> MainScreen()
                1 -> RentScreen()
                2 -> HistoryScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabsDashBoardPreview() {
    TabsDashboardContent(
        onLogoutClick = {},
        onProfileClick = {},
        onInfoClick = {}
    )
}
