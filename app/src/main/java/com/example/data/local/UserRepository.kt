package com.example.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class UserRepository(private val userDao: UserDao) {

    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): Result<UserEntity> = withContext(Dispatchers.IO) {
        val trimmedEmail = email.trim().lowercase()
        val trimmedName = name.trim()

        if (trimmedName.isBlank()) {
            return@withContext Result.failure(IllegalArgumentException("Please enter your name"))
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(trimmedEmail).matches()) {
            return@withContext Result.failure(IllegalArgumentException("Please enter a valid email address"))
        }
        if (password.length < 6) {
            return@withContext Result.failure(IllegalArgumentException("Password must be at least 6 characters"))
        }

        val existingUser = userDao.getUserByEmail(trimmedEmail)
        if (existingUser != null) {
            return@withContext Result.failure(IllegalStateException("An account with this email already exists"))
        }

        val userEntity = UserEntity(
            name = trimmedName,
            email = trimmedEmail,
            passwordHash = hashPassword(password)
        )

        try {
            val id = userDao.insertUser(userEntity)
            Result.success(userEntity.copy(id = id))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(
        email: String,
        password: String
    ): Result<UserEntity> = withContext(Dispatchers.IO) {
        val trimmedEmail = email.trim().lowercase()

        if (trimmedEmail.isBlank()) {
            return@withContext Result.failure(IllegalArgumentException("Please enter your email"))
        }
        if (password.isBlank()) {
            return@withContext Result.failure(IllegalArgumentException("Please enter your password"))
        }

        val user = userDao.getUserByEmail(trimmedEmail)
            ?: return@withContext Result.failure(IllegalArgumentException("No account found with this email"))

        val expectedHash = hashPassword(password)
        if (user.passwordHash != expectedHash) {
            return@withContext Result.failure(IllegalArgumentException("Incorrect password. Please try again."))
        }

        Result.success(user)
    }
}
