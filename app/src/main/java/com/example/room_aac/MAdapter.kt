package com.example.room_aac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MAdapter: RecyclerView.Adapter<MAdapter.MViewHolder>() {

    var userList: ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_view, parent, false)

        return MViewHolder(view)

    }

    override fun getItemCount(): Int {

        return userList.size

    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {

        holder.onBind(userList[position])

    }

    class MViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewAge: TextView = itemView.findViewById(R.id.textViewAge)
        private val textViewHobby: TextView = itemView.findViewById(R.id.textViewHobby)

        fun onBind(user: User) {

            textViewName.text = user.name
            textViewAge.text = user.age
            textViewHobby.text = user.hobby

        }

    }

}
