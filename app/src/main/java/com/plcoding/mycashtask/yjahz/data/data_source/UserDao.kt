package com.plcoding.mycashtask.yjahz.data.data_source

import androidx.room.*
import com.plcoding.mycashtask.yjahz.data.model.user.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(user: User)

    @Query("SELECT * FROM user")
    fun getUserProfile(): Flow<User?>
}