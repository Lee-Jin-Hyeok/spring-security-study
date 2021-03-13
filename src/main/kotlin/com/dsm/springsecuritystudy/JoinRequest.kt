package com.dsm.springsecuritystudy

data class JoinRequest(
    val accountId: String,
    val accountPassword: String,
    val accountName: String,
)