package com.example.dbm.popularmovieskt.util

import androidx.annotation.StringRes

data class MessageWrapper(
    @StringRes val messageResource: Int = 0,
    val argForResource: String? = null
)