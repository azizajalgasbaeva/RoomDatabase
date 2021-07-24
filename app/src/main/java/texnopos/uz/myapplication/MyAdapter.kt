package texnopos.uz.myapplication

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.myitem.view.*
import texnopos.uz.myapplication.data.User

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MViewHolder>() {
    var onMoreClicked:(user: User, view : View) ->Unit={_,_->}
    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModel(user: User, position: Int) {
            itemView.tvname.text = "${user.name} ${user.surname}"
            itemView.tvphone.text = "${user.phonenumeber}"
            itemView.ivmore.setOnClickListener {
                onMoreClicked.invoke(user, it)

            }
        }
    }
    fun removeItem(user: User){
        var index=numbers.indexOf(user)
        var list=numbers.toMutableList()
        list.remove(user)
        numbers=list
        notifyItemRemoved(index)
        notifyItemRangeChanged(0, numbers.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.myitem, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.populateModel(numbers[position], position)
    }

    override fun getItemCount()=numbers.size
    var numbers: List<User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
}