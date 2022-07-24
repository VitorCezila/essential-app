package com.cezila.essential.presentation.search_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun SearchDrinkItem(
    drink: Drink,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(drink.image)
                .build(),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                ),
            colorFilter = ColorFilter.tint(FilmItem, BlendMode.Multiply)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = drink.name,
            fontSize = 14.sp,
            fontFamily = Nuosu,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(6.dp))

        DifficultyIcons(
            drink = drink,
            modifier = Modifier
                .width(60.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = drink.author,
            fontFamily = Nuosu,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}