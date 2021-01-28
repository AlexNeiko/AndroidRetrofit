package com.alexneiko.androidretrofitrestapi.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexneiko.androidretrofitrestapi.DetailsActivity
import com.alexneiko.androidretrofitrestapi.R
import kotlinx.android.synthetic.main.user_cardview_item.view.*


class UserAdapter(private val mData: List<UserDTO>, private val mContext: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_cardview_item,
            parent, false)

        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = mData[position]

        holder.userCardNameTV.text = item.firstName + " " + item.lastName

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("image", mData[position].image)
            intent.putExtra("firstname", mData[position].firstName)
            intent.putExtra("lastname", mData[position].lastName)
            intent.putExtra("email", mData[position].email)
            mContext.startActivity(intent)
        })



    }

    //
    override fun getItemCount() = mData.size



    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val userCardNameTV: TextView = itemView.userCardNameTV

    }
}
