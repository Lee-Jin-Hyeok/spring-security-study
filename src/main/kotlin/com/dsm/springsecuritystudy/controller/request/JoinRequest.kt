package com.dsm.springsecuritystudy.controller.request

data class JoinRequest(
    val accountId: String,
    val accountPassword: String,
)