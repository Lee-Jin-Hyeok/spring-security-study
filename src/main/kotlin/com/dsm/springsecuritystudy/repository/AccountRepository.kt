package com.dsm.springsecuritystudy.repository

import com.dsm.springsecuritystudy.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, String>