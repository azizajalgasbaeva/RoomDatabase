package texnopos.uz.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add.*
import kotlinx.android.synthetic.main.dialog_add.view.*
import texnopos.uz.myapplication.data.MyDao
import texnopos.uz.myapplication.data.MyDatabase
import texnopos.uz.myapplication.data.User

class MainActivity : AppCompatActivity() {
    private lateinit var dao: MyDao
    private val myAdapter = MyAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myAdapter.onMoreClicked = { user, view ->
            val popupMenu: PopupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.item_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.itemEdit -> {
                        onEdit(user)
                    }
                    R.id.itemDelete -> {
                        dao.deleteuser(user)
                        myAdapter.removeItem(user)
                    }
                    R.id.itemCall -> {

                    }

                }
                return@setOnMenuItemClickListener true

            }
            popupMenu.show()
        }
        mainRecyclerView.adapter = myAdapter
        setData()
        addactionbutton.setOnClickListener {
            var view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null, false)
            var dialog = AlertDialog.Builder(this)
                .setTitle("Adding new user")
                .setView(R.layout.dialog_add)
                .setPositiveButton("Add") { dialog, which ->
                    var user = User(
                        name = view.etName.text.toString(),
                        surname = view.etsurname.text.toString(),
                        phonenumeber = view.etphone.text.toString()
                    )
                    addDataToDB(user)
                    Log.d("magliwmat", view.etName.text.toString())
                    Log.d("magliwmat", view.etName.text.toString())
                    Log.d("magliwmat", view.etName.text.toString())
                }

                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }
            dialog.show()
        }

    }

    fun setData() {
        dao = MyDatabase.getInstance(this).studentsDao()
        myAdapter.numbers = dao.getAllStudents()
    }

    fun addDataToDB(user: User) {
        dao.insertuser(user)
        setData()
    }

    fun onEdit(user: User) {
        var view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null, false)
        view.etName.setText(user.name)
        view.etsurname.setText(user.surname)
        view.etphone.setText(user.phonenumeber)
        var dialog = AlertDialog.Builder(this)
            .setTitle("Edit user")
            .setView(R.layout.dialog_add)
            .setPositiveButton("Edit") { dialog, which ->
                user.name = view.etName.text.toString()
                user.surname = view.etsurname.text.toString()
                user.phonenumeber = view.etphone.text.toString()
                dao.updateuser(user)
                setData()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
        dialog.show()
    }

}