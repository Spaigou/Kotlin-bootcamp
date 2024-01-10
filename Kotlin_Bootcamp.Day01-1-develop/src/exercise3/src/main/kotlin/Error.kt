package org.example

open class Response(val errorCode: Int)

data class Success(val code: Int, val message: String = "The request processed successfully") : Response(code)

sealed class Error(val code: Int, val title: String, val description: String) : Response(code) {
    data object UserNotIdentifiedError : Error(
        1000,
        "The user is not identified",
        "User is not identified in the system. Try again later."
    )

    data object SessionExpiredError : Error(
        1001,
        "The session is expired",
        "Your session has been expired."
    )

    data object NoConnectionError : Error(
        1002,
        "No connection",
        "There is no internet connection. Try later."
    )

    data object VerificationFailedError : Error(
        1003,
        "The device has failed the verification",
        "Your deices have failed the verification. Try again later."
    )
}

data class UnknownError(val error: Int) : Error(
    error,
    "Error code: $error",
    "Unknown error. Please, try again later"
)