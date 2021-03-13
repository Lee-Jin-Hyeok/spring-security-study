package com.dsm.springsecuritystudy

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, String>