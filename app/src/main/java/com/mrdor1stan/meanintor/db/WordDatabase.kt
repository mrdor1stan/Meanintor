package com.mrdor1stan.meanintor.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import com.mrdor1stan.meanintor.data.WordCard
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Database(entities = [WordCard::class], version = 1)
abstract class WordDatabase: RoomDatabase(){
    abstract fun wordDao():WordDao
}

@Dao
interface WordDao {
    @Query("SELECT * FROM wordcard WHERE id=(:id)")
    fun getWordCard(id: UUID): Flow<List<WordCard>>
}