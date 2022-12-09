package com.example.procod.presentation.challenge_tab.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.procod.model.Label

@Composable
fun LabelItem(
    label: Label,
    size: TextUnit,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(50),
        backgroundColor = color
    ) {
        Text(
            text = label.Label!!,
            fontSize = size,
            color = Color.White
        )
    }
}