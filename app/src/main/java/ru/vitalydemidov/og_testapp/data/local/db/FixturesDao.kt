package ru.vitalydemidov.og_testapp.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.data.model.Fixture

@Dao
interface FixturesDao {

    @Query("""
        SELECT * FROM fixtures
        WHERE type = :type AND competition_stage_competition_id = :competitionId
        ORDER BY date ASC
    """)
    fun getAllByDateAsc(type: String, competitionId: Long?): Flowable<List<Fixture>>

    @Query("""
        SELECT * FROM fixtures
        WHERE type = :type AND competition_stage_competition_id = :competitionId
        ORDER BY date DESC
    """)
    fun getAllByDateDesc(type: String, competitionId: Long?): Flowable<List<Fixture>>

    @Insert
    fun insertAll(fixtures: List<Fixture>)

    @Query("DELETE FROM fixtures WHERE type = :type")
    fun deleteAllByType(type: String)

}