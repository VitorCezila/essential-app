package com.cezila.essential.presentation.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cezila.essential.ui.theme.GrayUnuse
import com.cezila.essential.ui.theme.YellowSoft
import com.cezila.essential.util.Commom.route

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { i ->
            val selected = i.route == route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onItemClick(i)
                },
                selectedContentColor = YellowSoft,
                unselectedContentColor = GrayUnuse,
                icon = {
                    Column {
                        Icon(
                            painter = painterResource(id = i.icon),
                            contentDescription = i.name,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                                .align(CenterHorizontally)
                        )
                        Text(
                            text = i.name
                        )
                    }
                },
                modifier = Modifier.background(Color(0xFF181818))
            )
        }
    }
}