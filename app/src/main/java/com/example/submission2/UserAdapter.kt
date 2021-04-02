package com.example.submission2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission2.databinding.UserItemsBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val mData = ArrayList<UserItems>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallBack
    }

    fun setData(items: ArrayList<UserItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): UserAdapter.UserViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_items, viewGroup, false)
        return UserViewHolder(mView)

    }

    override fun onBindViewHolder(userViewHolder: UserViewHolder, position: Int) {
       userViewHolder.bind(mData[position])

        userViewHolder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(mData[userViewHolder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = mData.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemsBinding.bind(itemView)
        fun bind(userItems: UserItems) {
            with(itemView){
                binding.tvItemName.text = userItems.name
                binding.tvItemUsername.text = userItems.username
                //binding.imgItemAvatar.setImageResource(userItems.avatar)
                Glide.with(itemView.context)
                    .load(userItems.avatar)
                    .apply(RequestOptions().override(55, 55))
                    .into(binding.imgItemAvatar)

                setOnClickListener{onItemClickCallback.onItemClicked(userItems)}
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(userItems: UserItems)
    }

}