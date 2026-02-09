package com.finch.api.user.application.service.command

import com.finch.api.user.application.port.`in`.FormLoginUseCase
import com.finch.api.user.application.port.out.UserRepository
import com.finch.api.user.domain.entity.User.Companion.createFormUserBuilder
import com.finch.api.user.presentation.dto.request.SignUpRequest
import com.finch.global.exception.handleException.DuplicateEmailException
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class FormLoginService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : FormLoginUseCase {

    @Transactional
    override fun signUp(request: SignUpRequest) {
        if (userRepository.existsByEmail(request.email)) {
            throw DuplicateEmailException()
        }

        val userBuilder = createFormUserBuilder(request, passwordEncoder.encode(request.password))
        val user = userRepository.save(userBuilder)
    }

}