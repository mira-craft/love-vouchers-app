package com.example.lovevouchers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterSection(
    selectedFilter: VoucherType?,
    onFilterSelected: (VoucherType?) -> Unit
) {

    val containerColor = Color(0xFFF6F1EA)   // ganz leicht beige
    val selectedColor = Color(0xFFFAE7E8)    // dein Rosa

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = containerColor,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(4.dp)
    ) {

        SegmentedItem(
            content = {
                Text(
                    text = "All",
                    fontSize = 18.sp,
                    color = Color(0xFF6E6E73)
                )
            },
            isSelected = selectedFilter == null,
            selectedColor = selectedColor,
            modifier = Modifier.weight(1f)
        ) {
            onFilterSelected(null)
        }

        SegmentedItem(
            content = {
                HeartText(
                    text = "In Person",
                    fontSize = 18.sp,
                    heartColor = Color(0xFFE94B5A)
                )
            },
            isSelected = selectedFilter == VoucherType.IN_PERSON,
            selectedColor = selectedColor,
            modifier = Modifier.weight(1f)
        ) {
            onFilterSelected(VoucherType.IN_PERSON)
        }

        SegmentedItem(
            content = {
                HeartText(
                    text = "Remote",
                    fontSize = 18.sp,
                    heartColor = Color(0xFF3A7BD5),
                )
            },
            isSelected = selectedFilter == VoucherType.REMOTE,
            selectedColor = selectedColor,
            modifier = Modifier.weight(1f)
        ) {
            onFilterSelected(VoucherType.REMOTE)
        }
    }
}

@Composable
private fun SegmentedItem(
    content: @Composable () -> Unit,
    isSelected: Boolean,
    selectedColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(
                color = if (isSelected) selectedColor else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun HeartText(
    text: String,
    heartColor: Color,
    fontSize: TextUnit
) {

    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = heartColor)) {
                append("♡ ")
            }
            append(text)
        },
        fontSize = 18.sp,
        color = Color(0xFF6E6E73)
    )
}