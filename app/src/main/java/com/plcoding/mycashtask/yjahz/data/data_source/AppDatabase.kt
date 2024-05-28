package com.plcoding.mycashtask.yjahz.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.plcoding.mycashtask.di.AppModule
import com.plcoding.mycashtask.yjahz.data.model.user.User
import com.plcoding.mycashtask.yjahz.data.util.Converters
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [User::class], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }

    class PrepopulateCallback @Inject constructor(
        @AppModule.ApplicationScope private val scope: CoroutineScope,
        @ApplicationContext private val context: Context,
        private val movieDao: Provider<UserDao>,
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}