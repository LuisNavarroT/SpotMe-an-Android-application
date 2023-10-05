package com.luisnavarro.spotme.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "user_email")
    var email: String,
    @ColumnInfo(name = "user_password")
    var password: String
    )






