package texnopos.uz.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var surname: String,
    var name: String,
    var phonenumeber: String
)