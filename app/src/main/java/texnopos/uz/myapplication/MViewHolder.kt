package texnopos.uz.myapplication

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.myitem.view.*
import texnopos.uz.myapplication.data.User

class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(user: User, position: Int) {
        itemView.tvname.text = "${user.name} ${user.surname}"
        itemView.tvphone.text = "${user.phonenumeber}"
itemView.ivmore.setOnClickListener {

}
    }
}