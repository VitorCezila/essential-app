package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cezila.essential.domain.model.Step
import com.cezila.essential.ui.theme.Nuosu
import com.cezila.essential.ui.theme.Orange

@Composable
fun StepItem(
    modifier: Modifier = Modifier,
    step: Step
) {

    Row(modifier = modifier) {
        Text(
            text = step.number.toString(),
            color = Orange,
            fontSize = 18.sp,
            fontFamily = Nuosu,
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = step.step,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = Nuosu,
        )
    }

}