package br.com.fiap.affily.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.fiap.affily.models.entities.Child

@Dao
interface ChildDAO {

    @Query("SELECT * FROM crianca ")
    fun getChild(): LiveData<List<Child>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(child: Child)

    @Query("DELETE FROM crianca ")
    suspend fun deleteAll()

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(child: Child)


}