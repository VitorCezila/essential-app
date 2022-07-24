package com.cezila.essential.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.presentation.DifficultyIcons
import com.cezila.essential.ui.theme.FilmItem
import com.cezila.essential.ui.theme.Nuosu


@Composable
fun TodayDrinkItem(
    drink: Drink,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(drink.image)
                .build(),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(),
            colorFilter = ColorFilter.tint(FilmItem, BlendMode.Multiply)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 10.dp)
        ) {
            Text(
                text = drink.name,
                fontFamily = Nuosu,
                color = Color.White,
                fontSize = 24.sp
            )

            DifficultyIcons(
                drink = drink,
                modifier = Modifier.width(120.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = drink.author,
                fontFamily = Nuosu,
                fontSize = 12.sp,
                color = Color.White,
            )
        }
    }

}