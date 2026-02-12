package com.example.lovevouchers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

private val ProvicaliFont = FontFamily(
    Font(R.font.provicali, FontWeight.Normal)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoveVouchersApp()
        }
    }
}

@Composable
fun LoveVouchersApp() {

    val context = LocalContext.current
    val dataStore = remember { VoucherDataStore(context) }
    val scope = rememberCoroutineScope()

    var vouchers by remember { mutableStateOf(VoucherRepository.getVouchers()) }
    var voucherToRedeem by remember { mutableStateOf<Voucher?>(null) }
    var selectedFilter by remember { mutableStateOf<VoucherType?>(null) }

    // 🔥 Redeemed State laden
    LaunchedEffect(Unit) {
        vouchers.forEach { voucher ->
            scope.launch {
                dataStore.isVoucherRedeemed(voucher.id).collect { redeemed ->
                    voucher.isRedeemed = redeemed
                }
            }
        }
    }

    Scaffold(
        containerColor = Color.White
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Header()

            Spacer(modifier = Modifier.height(16.dp))

            FilterSection(
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            val displayedVouchers =
                if (selectedFilter == null) {

                    val remote = vouchers.filter { it.type == VoucherType.REMOTE }
                    val inPerson = vouchers.filter { it.type == VoucherType.IN_PERSON }

                    remote.zip(inPerson)
                        .flatMap { listOf(it.first, it.second) } +
                            remote.drop(inPerson.size) +
                            inPerson.drop(remote.size)

                } else {
                    vouchers.filter { it.type == selectedFilter }
                }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                items(displayedVouchers) { voucher ->
                    VoucherCard(
                        voucher = voucher,
                        onRedeem = {
                            voucherToRedeem = voucher
                        }
                    )
                }
            }
        }
    }

    // 🔥 Confirmation Dialog im Parent (State Hoisting korrekt)
    voucherToRedeem?.let { voucher ->

        AlertDialog(
            onDismissRequest = { voucherToRedeem = null },
            title = {
                Text("Confirm Redemption")
            },
            text = {
                Text(
                    "Are you sure you want to redeem this voucher? " +
                            "This action cannot be undone.",
                    textAlign = TextAlign.Start
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        scope.launch {
                            dataStore.setVoucherRedeemed(voucher.id)
                            voucher.isRedeemed = true
                        }
                        voucherToRedeem = null
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { voucherToRedeem = null }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun Header() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        // ZENTRIERTER TITEL
        Text(
            text = "LOVE VOUCHERS",
            fontFamily = ProvicaliFont,
            fontSize = 44.sp,
            letterSpacing = 0.18.em,
            color = Color(0xFFB56576),
            modifier = Modifier.align(Alignment.Center)
                .align(Alignment.Center)
                .fillMaxWidth(),          // ← DAS ist entscheidend
                textAlign = TextAlign.Center  // ← und das
        )

        // LINKES großes Herz
        Text(
            text = "♡",
            fontSize = 26.sp,
            color = Color(0xFFB56576),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 24.dp, y = (-8).dp)
                .graphicsLayer { rotationZ = -12f }
        )

        // LINKES kleines Herz
        Text(
            text = "♡",
            fontSize = 16.sp,
            color = Color(0xFF3A7BD5),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 75.dp, y = (-16).dp)
                .graphicsLayer { rotationZ = 10f }
        )

        // RECHTES Herz
        Text(
            text = "♡",
            fontSize = 22.sp,
            color = Color(0xFFE94B5A),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-28).dp, y = 6.dp)
        )
    }
}

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (selected) Color(0xFFE94B5A)
        else Color(0xFFE5E5EA)

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Text(text)
    }
}