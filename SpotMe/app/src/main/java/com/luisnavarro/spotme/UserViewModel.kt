package com.luisnavarro.spotme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisnavarro.spotme.db.User
import com.luisnavarro.spotme.db.UserDao
import kotlinx.coroutines.launch

class UserViewModel(private val dao: UserDao):ViewModel() {

    val users = dao.getAllUsers()

    fun insertUser(user: User)=viewModelScope.launch {
        dao.insertUser(user)
    }

    fun updateUser(user: User)=viewModelScope.launch {
        dao.updateUser(user)
    }

    fun deleteUser(user: User)=viewModelScope.launch {
        dao.deleteUser(user)
    }

    suspend fun getUserByEmail(email: String) = dao.getUserByEmail(email)

}