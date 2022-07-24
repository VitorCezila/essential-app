package com.cezila.essential.presentation.about_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cezila.essential.R
import com.cezila.essential.presentation.bottom_nav.BottomNavItem
import com.cezila.essential.presentation.bottom_nav.BottomNavigationBar
import com.cezila.essential.presentation.bottom_nav.bottomNavItems
import com.cezila.essential.ui.theme.BackgroundColor
import com.cezila.essential.ui.theme.Nuosu
import com.cezila.essential.util.Commom
import com.cezila.essential.util.Commom.route
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination("about")
fun AboutScreen(navigator: DestinationsNavigator) {

    route = "about"

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                bottomNavItems,
                onItemClick = {
                    route = it.route
                    if(route != "about") navigator.navigate(route = route)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(BackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.coffee_icon),
                contentDescription = null,
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Obrigado por baixar meu aplicativo",
                fontSize = 14.sp,
                fontFamily = Nuosu,
                color = Color.White,
                modifier = Modifier.width(220.dp)
            )

            Spacer(Modifier.height(300.dp))

            Text(
                text = "Espresso vector created by catalyststuff",
                fontSize = 14.sp,
                fontFamily = Nuosu,
                color = Color.White,
                modifier = Modifier.width(220.dp)
            )

        }
    }
}