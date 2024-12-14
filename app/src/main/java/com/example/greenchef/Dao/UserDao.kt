package com.example.greenchef.Dao

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.greenchef.Models.User

@Dao
interface UserDao {

    // Create new user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    // Get user by email
    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    fun isUserExists(email: String): Int

    // Check authentication
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUserByEmailAndPassword(username: String, password: String): User?
}