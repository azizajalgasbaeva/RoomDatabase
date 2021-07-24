package texnopos.uz.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [User::class], version = 2)
abstract class MyDatabase : RoomDatabase() {
    companion object{
        lateinit var INSTANCE : MyDatabase
        fun getInstance(context: Context) : MyDatabase{
if(!::INSTANCE.isInitialized){
    INSTANCE= Room.databaseBuilder(
        context,
        MyDatabase::class.java, "students-database"
    )
        .createFromAsset("fruits.db")
        .allowMainThreadQueries()
        .build()
}
            return INSTANCE
        }
    }
    abstract fun studentsDao() : MyDao
}