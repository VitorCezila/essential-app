package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cezila.essential.ui.theme.BackgroundColor
import com.cezila.essential.ui.theme.Film
import com.cezila.essential.ui.theme.Nuosu
import com.cezila.essential.ui.theme.Orange
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun DrinkScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
        ) {
            // só montar a tela após a requisição
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cookienameddesire.com/wp-content/uploads/2016/05/passion-fruit-mojito-recipe-photo-1.jpg")
                    .build(),
                contentDescription = null,
                alignment = Center,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(),
                colorFilter = ColorFilter.tint(Film, BlendMode.Multiply)
            )
            Text(
                text = "Passionfruit\nMojito",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = Nuosu,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 30.dp, bottom = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column {
            Text(
                text = "Ingredients",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = Nuosu,
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp)
            ) {
                Column {
                    Text(
                        text = "Alcohol:",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = Nuosu
                    )
                    // lazy column
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "2 oz White rum",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "3 Mint leaves",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.width(100.dp))

                Column {
                    Text(
                        text = "Garnish:",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = Nuosu
                    )
                    // lazy column
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Lime wheel",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Passionfruit",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Text(
                    text = "Juice",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = Nuosu
                )
                // lazy column
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "2/4 oz Fresh lime juice",
                    color = Color.White,
                    fontSize = 12.sp
                )
                Text(
                    text = "1/4 oz Passionfruit juice",
                    color = Color.White,
                    fontSize = 12.sp
                )
                Text(
                    text = "1/2 oz Simple syrup",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = { },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, backgroundColor = Orange),
                modifier = Modifier
                    .padding(start = 60.dp)
                    .width(250.dp)
                    .height(56.dp)
            ){
                Text( text = "Start mixing")
            }
        }
    }
}