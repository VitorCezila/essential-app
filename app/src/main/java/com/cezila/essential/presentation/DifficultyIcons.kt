package com.cezila.essential.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cezila.essential.R
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.ui.theme.RatedColor
import com.cezila.essential.ui.theme.UnratedColor

@Composable
fun DifficultyIcons(
    modifier: Modifier = Modifier,
    drink: Drink
) {
    Row(
        modifier = modifier
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = R.drawable.icon_circle),
                contentDescription = null,
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .padding(2.dp),
                tint = if (i <= drink.difficulty) RatedColor else UnratedColor
            )
        }
    }

}