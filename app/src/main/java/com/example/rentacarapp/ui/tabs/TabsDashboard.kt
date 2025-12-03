package com.example.rentacarapp.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rentacarapp.R
import com.example.rentacarapp.ui.cars.history.screen.HistoryScreen
import com.example.rentacarapp.ui.cars.main.screen.MainScreen
import com.example.rentacarapp.ui.cars.rental.screen.RentScreen
import kotlinx.coroutines.launch

sealed class TabItem( val title:Int, val icon : ImageVector){
    object Main: TabItem(title = R.string.main, Icons.Default.Home)
    object Rent: TabItem(title = R.string.rent, Icons.Default.CarRental)
    object History: TabItem(title = R.string.history,Icons.Default.History)
}

@Composable
fun TabsDashboard() {
    val tabTitles = listOf(TabItem.Main,TabItem.Rent,TabItem.History)
    val pagerState = rememberPagerState { tabTitles.size }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()){
        TabRow(selectedTabIndex = pagerState.currentPage,
            containerColor = Color(0xFF0e72a6),
            contentColor = Color.White,
            indicator = {},
            divider = {
                HorizontalDivider(thickness = 3.dp,
                    color = Color(0xFF084A6E)
                )
            }
        ){
            tabTitles.forEachIndexed{ index, tab ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = if(selected)Modifier
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
                    text = {Text(text = stringResource(id = tab.title)) },
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

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pagerIndex ->
            when(pagerIndex){
                0 -> MainScreen()
                1 -> RentScreen()
                2 -> HistoryScreen()
            }
        }
    }
}

@Preview(showBackground =true)
@Composable
fun PreviewTabs(){
    TabsDashboard()
}