package io.github.jerrymatera.projectsmanager.data.utils

sealed class NetworkException(message: String, cause: Throwable) : Exception(message, cause) {

    data class UnauthorizedException(override val message: String, override val cause: Throwable) :
        NetworkException(message, cause)

    data class BadRequestException(override val message: String, override val cause: Throwable) :
        NetworkException(message, cause)

    data class NotFoundException(override val message: String, override val cause: Throwable) :
        NetworkException(message, cause)

    data class UnknownException(override val message: String, override val cause: Throwable) :
        NetworkException(message, cause)
}