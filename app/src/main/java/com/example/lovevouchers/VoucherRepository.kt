package com.example.lovevouchers

object VoucherRepository {

    fun getVouchers(): List<Voucher> {
        return listOf(

            // 💙 REMOTE VOUCHERS

            Voucher(
                id = "remote_1",
                title = "You Pick the Game, I Try",
                description = "I will play something you love.\nExpectations low, love high.",
                type = VoucherType.REMOTE
            ),

            Voucher(
                id = "remote_2",
                title = "Gaming Talk Monologue Pass",
                description = "You explain your game in detail.\nI listen. I nod seriously.",
                type = VoucherType.REMOTE
            ),

            Voucher(
                id = "remote_3",
                title = "Recipe Screenshot",
                description = "You send me one recipe you love.\nI promise not to cook it.",
                type = VoucherType.REMOTE
            ),

            Voucher(
                id = "remote_4",
                title = "Recipe Screenshot",
                description = "You send me one recipe you love.\nWe cook it together.",
                type = VoucherType.REMOTE
            ),

            Voucher(
                id = "remote_5",
                title = "I Admit Your Food Looks Better Than Mine",
                description = "Redeem anytime. Unlimited smugness allowed.",
                type = VoucherType.REMOTE
            ),

            Voucher(
                id = "remote_6",
                title = "You’re Doing Enough",
                description = "Redeem when self-doubt shows up.",
                type = VoucherType.REMOTE
            ),

            Voucher(
                id = "remote_7",
                title = "DISTANCE SUCKS,\nWE’RE ALLOWED TO SAY IT",
                description = "Valid always.",
                type = VoucherType.REMOTE
            ),

            // ❤️ IN PERSON VOUCHERS

            Voucher(
                id = "inperson_1",
                title = "Shower Foam Spa Upgrade",
                description = "I turn your regular shower into a dramatic bubble situation.\nZero dignity, maximum fun.",
                type = VoucherType.IN_PERSON
            ),

            Voucher(
                id = "inperson_2",
                title = "Cinema Date",
                description = "You choose the film.\nI reserve the right to veto extreme gore, horror, or emotional trauma.",
                type = VoucherType.IN_PERSON
            ),

            Voucher(
                id = "inperson_3",
                title = "One Real Secret",
                description = "Redeem for something I’ve never told you before.\nMildly mysterious, not illegal.",
                type = VoucherType.IN_PERSON
            ),

            Voucher(
                id = "inperson_4",
                title = "Cook Together (In Person Edition)",
                description = "You lead. I assist.\nI will not pretend I know what I’m doing.",
                type = VoucherType.IN_PERSON
            ),

            Voucher(
                id = "inperson_5",
                title = "Delivery Night — Chef’s Day Off",
                description = "You choose what we order.\nNo debates, no ‘are you sure?’",
                type = VoucherType.IN_PERSON
            ),

            Voucher(
                id = "inperson_6",
                title = "Stargazing Date",
                description = "You pick the time and place.\nI handle snacks and drinks.",
                type = VoucherType.IN_PERSON
            ),

            Voucher(
                id = "inperson_7",
                title = "Board Game of Your Choice",
                description = "Snacks, rules, and good vibes included.",
                type = VoucherType.IN_PERSON
            )
        )
    }
}