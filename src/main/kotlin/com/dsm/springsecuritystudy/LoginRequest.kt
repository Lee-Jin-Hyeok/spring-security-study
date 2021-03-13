package com.dsm.springsecuritystudy

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequest(
    @JsonProperty("id")
    val accountId: String,
    @JsonProperty("password")
    val accountPassword: String,
)