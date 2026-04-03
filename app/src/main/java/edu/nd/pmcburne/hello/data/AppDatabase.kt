package edu.nd.pmcburne.hello

import android.content.Context
import androidx.room.*

@Database(entities = [LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "locations_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}