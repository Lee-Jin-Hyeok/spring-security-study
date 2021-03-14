package com.dsm.springsecuritystudy.service

import com.dsm.springsecuritystudy.exception.AccountNotFoundException
import com.dsm.springsecuritystudy.repository.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val accountRepository: AccountRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String) =
        accountRepository.findByIdOrNull(username) ?: throw AccountNotFoundException(username)
}