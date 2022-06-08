package com.cezila.essential.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.ui.theme.Nuosu
import com.cezila.essential.R
import com.cezila.essential.ui.theme.FilmItem
import com.cezila.essential.ui.theme.GrayUnuse
import com.cezila.essential.ui.theme.Yellow


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
            Row(
                modifier = Modifier
                    .width(60.dp)
            ) {
                for (i in 1..5) {
                    Icon(
                        painter = painterResource(id = R.drawable.black_start),
                        contentDescription = null,
                        modifier = Modifier
                            .width(12.dp)
                            .height(12.dp),
                        tint = if (i <= drink.difficulty) Yellow else GrayUnuse
                    )
                }
            }

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