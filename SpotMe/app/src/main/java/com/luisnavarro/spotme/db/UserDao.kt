package com.luisnavarro.spotme.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_data_table")
    fun getAllUsers():LiveData<List<User>>

    @Query("SELECT * FROM user_data_table WHERE user_email = :email")
    suspend fun getUserByEmail(email: String): User?
}


