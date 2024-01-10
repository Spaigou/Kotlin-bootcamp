package org.example

fun handleResponse(response: Response) {
    when (response) {
        is Success -> {
            println("Success:\nCode: ${response.code}\nMessage: ${response.message}")
        }

        is Error -> {
            when (response) {
                is UnknownError -> {
                    println("Unknown error:")
                    println(response.title)
                    println("Description: ${response.description}")
                }

                else -> {
                    println("${response::class.simpleName}:")
                    println("Code: ${response.code}")
                    println("Title: ${response.title}")
                    println("Description: ${response.description}")
                }
            }

        }
    }
}

fun handleCode(code: Int): Response {
    return when (code) {
        in 200..201 -> Success(code)
        1000 -> Error.UserNotIdentifiedError
        1001 -> Error.SessionExpiredError
        1002 -> Error.NoConnectionError
        1003 -> Error.VerificationFailedError
        else -> UnknownError(code)
    }
}

fun main() {
    print("Type a response code: ")
    val code = readln().toIntOrNull() ?: throw NumberFormatException("Must be integer numbers only")
    val response = handleCode(code)
    handleResponse(response)
}