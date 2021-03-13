package com.dsm.springsecuritystudy

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "account")
class Account(

    @Id @Column(name = "id")
    val id: String,

    @Column(name = "password")
    private val password: String,

    @Column(name = "name")
    val name: String,

    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableList<String>,
) : UserDetails {

    override fun getAuthorities() =
        roles.map { SimpleGrantedAuthority(it) }

    override fun getUsername() = id

    override fun getPassword() = password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}