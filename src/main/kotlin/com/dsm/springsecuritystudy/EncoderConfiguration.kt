package com.dsm.springsecuritystudy

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class EncoderConfiguration {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder() as DelegatingPasswordEncoder
//        passwordEncoder.setDefaultPasswordEncoderForMatches(BCryptPasswordEncoder())
//        return passwordEncoder
//    }
//
//    DelegatingPasswordEncoder 를 사용하면 유동적인 해시 방식을 사용할 수 있다.
//    대신 암호화된 패스워드 앞에 {해시함수}과 같은 문자열이 추가된다.
//    예를 들어 BCrypt 해시를 사용하면 '{bcrypt}해시문자열' 과 같은 형태로 데이터베이스에 저장된다.
//    데이터베이스에 이런식으로 저장하는 것은 좋지 않다고 판단하여 위와 같이 해시 방식을 고정하였다.
}