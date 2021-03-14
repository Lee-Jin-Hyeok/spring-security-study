package com.dsm.springsecuritystudy

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class AccountController(
    private val accountRepository: AccountRepository,
    private val tokenProvider: TokenProvider,
    private val passwordEncoder: PasswordEncoder,
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        val account = accountRepository.findByIdOrNull(request.accountId) ?: throw AccountNotFoundException(request.accountId)
        println("request.accountPassword: ${request.accountPassword}")
        println("account.password: ${account.password}")
        return if (passwordEncoder.matches(request.accountPassword, account.password))
            LoginResponse(tokenProvider.createToken(account.id, account.roles, 1000 * 60 * 60))
        else
            throw AccountNotFoundException(request.accountId)
    }

    @PostMapping("/join")
    fun join(@RequestBody request: JoinRequest) {
        val password = passwordEncoder.encode(request.accountPassword)
        println("password: $password")
        accountRepository.save(
            Account(
                id = request.accountId,
                password = passwordEncoder.encode(request.accountPassword),
                name = request.accountName,
                roles = Collections.singletonList("ROLE_USER")
            )
        )
    }

    @GetMapping("/hello/a")
    fun helloTest() = 1

    @GetMapping("/hello")
    fun hello() = 2

    @GetMapping("/non-hello")
    fun nonHelloTest() = 3
}