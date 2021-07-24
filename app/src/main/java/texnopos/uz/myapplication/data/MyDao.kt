package texnopos.uz.myapplication.data

import androidx.room.*

@Dao
interface MyDao {
    @Query("SELECT * FROM students")
    fun getAllStudents(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertuser(user: User)

    @Delete
    fun deleteuser(user: User)

    @Update
    fun updateuser(user: User)


}