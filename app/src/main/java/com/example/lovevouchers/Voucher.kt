package com.example.lovevouchers

data class Voucher(
    val id: String,
    val title: String,
    val description: String,
    val type: VoucherType,
    var isRedeemed: Boolean = false
)