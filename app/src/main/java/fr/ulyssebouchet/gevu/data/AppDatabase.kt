package fr.ulyssebouchet.gevu.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Match::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun MatchDao(): MatchDao
}


