package com.example.procod.presentation.challenge_tab.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.procod.model.Label

@Composable
fun LabelItem2(
    label: Label,
    size: TextUnit,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(25),
        backgroundColor = color,
        modifier = modifier.padding(vertical = 0.dp).padding(horizontal = 0.dp)
    ) {
        Text(
            text = label.Label!!,
            fontSize = 13.sp,
            color = Color.White,
            modifier = modifier.padding(vertical = 1.dp).padding(horizontal = 5.dp).padding(start = 1.dp)

        )
    }
}