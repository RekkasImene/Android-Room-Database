package fr.mastersid.rekkas.roomdatabase.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.mastersid.rekkas.roomdatabase.adapters.Questions
import fr.mastersid.rekkas.roomdatabase.models.ClassJsonToKotlin

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List <Questions>)
    @Query("SELECT * FROM question_table")
    fun getQuestionList (): LiveData<List<Questions>>
}