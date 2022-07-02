package br.com.fiap.affily.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.affily.models.entities.Child
import java.security.AccessControlContext

@Database(entities = [Child::class], version = 1, exportSchema = false)
public abstract class ChildRoomDatabase: RoomDatabase(){
    abstract fun childDAO(): ChildDAO

    companion object{
        @Volatile
        private var INSTANCE : ChildRoomDatabase? = null

        fun getDatabase(context: Context) : ChildRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    ChildRoomDatabase::class.java,
                    "child_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}