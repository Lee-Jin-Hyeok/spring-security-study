package com.dsm.springsecuritystudy

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String) =
        accountRepository.findByIdOrNull(username) ?: throw AccountNotFoundException(username)
}