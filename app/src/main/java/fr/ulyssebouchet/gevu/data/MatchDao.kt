package fr.ulyssebouchet.gevu.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDao {
    @Query("SELECT * FROM `Match`")
    fun getAll(): List<Match>

    @Insert
    fun insert(match: Match)

    @Delete
    fun delete(match: Match)
}
