package com.example.lovevouchers

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.lovevouchers.ui.theme.CourierPrimeFont
import com.example.lovevouchers.ui.theme.OpenSauceFont

@Composable
fun VoucherCard(
    voucher: Voucher,
    onRedeem: () -> Unit
) {

    val borderColor = Color(0xFFB56576)
    val activeRedeemBackground = Color(0xFFFAE7E8)
    val backgroundColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .alpha(if (voucher.isRedeemed) 0.65f else 1f)
            .semantics {
                stateDescription =
                    if (voucher.isRedeemed) "Voucher redeemed"
                    else "Voucher not redeemed"
            }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {

            Canvas(modifier = Modifier.fillMaxSize()) {

                val w = size.width
                val h = size.height

                val radius = h / 7f
                val centerY = h / 2f

                val rectPath = Path().apply {
                    addRect(Rect(0f, 0f, w, h))
                }

                val leftCircle = Path().apply {
                    addOval(
                        Rect(
                            -radius,
                            centerY - radius,
                            radius,
                            centerY + radius
                        )
                    )
                }

                val rightCircle = Path().apply {
                    addOval(
                        Rect(
                            w - radius,
                            centerY - radius,
                            w + radius,
                            centerY + radius
                        )
                    )
                }

                val circles = Path.combine(
                    PathOperation.Union,
                    leftCircle,
                    rightCircle
                )

                val ticketPath = Path.combine(
                    PathOperation.Difference,
                    rectPath,
                    circles
                )

                drawPath(
                    path = ticketPath,
                    color = borderColor,
                    style = Stroke(width = 6f)
                )

                val inset = h * 0.06f
                val separatorX = w * 0.75f
                val dashEffect = PathEffect.dashPathEffect(floatArrayOf(18f, 12f))

                drawLine(
                    color = borderColor,
                    start = Offset(inset, inset),
                    end = Offset(w - inset, inset),
                    strokeWidth = 3f,
                    pathEffect = dashEffect
                )

                drawLine(
                    color = borderColor,
                    start = Offset(inset, h - inset),
                    end = Offset(w - inset, h - inset),
                    strokeWidth = 3f,
                    pathEffect = dashEffect
                )

                drawLine(
                    color = borderColor,
                    start = Offset(separatorX, inset),
                    end = Offset(separatorX, h - inset),
                    strokeWidth = 3f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 10f))
                )
            }

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .weight(0.75f)
                        .fillMaxHeight()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = voucher.title.uppercase(),
                        fontFamily = OpenSauceFont,
                        fontSize = 20.sp,
                        letterSpacing = 0.06.em,
                        color = borderColor,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = voucher.description,
                        fontFamily = CourierPrimeFont,
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = borderColor,
                        textAlign = TextAlign.Center
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 26.dp,
                            start = 8.dp,
                        )
                        .fillMaxHeight()
                        .background(
                            if (!voucher.isRedeemed) activeRedeemBackground
                            else backgroundColor
                        )
                        .clickable(enabled = !voucher.isRedeemed) {
                            onRedeem()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = if (voucher.isRedeemed) "REDEEMED" else "REDEEM",
                        fontFamily = OpenSauceFont,
                        fontSize = if (voucher.isRedeemed) 18.sp else 16.sp,
                        letterSpacing = if (voucher.isRedeemed) 0.16.em else 0.24.em,
                        color = borderColor,
                        maxLines = 1,
                        softWrap = false,
                        modifier = Modifier
                            .requiredWidth(IntrinsicSize.Max)
                            .rotate(90f)
                    )
                }
            }
        }
    }
}