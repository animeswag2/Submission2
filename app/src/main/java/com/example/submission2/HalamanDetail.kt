package com.example.submission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission2.databinding.ActivityHalamanDetailBinding

class HalamanDetail : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanDetailBinding


    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Profil User"
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayShowHomeEnabled(true)


        val tvObject : TextView = findViewById(R.id.tv_object_received)
        val imageViewAvatar : ImageView = findViewById(R.id.img_item_avatar1)

        val intent = intent.getParcelableExtra<UserItems>(EXTRA_USER) as UserItems
        fun bind(userItems: UserItems) {
                binding.tvItemName1.text = userItems.name
                binding.tvItemUsername1.text = userItems.username
                //binding.imgItemAvatar.setImageResource(userItems.avatar)
                Glide.with(this)
                    .load(userItems.avatar)
                    .apply(RequestOptions().override(55, 55))
                    .into(binding.imgItemAvatar1)
            }
/*
        val text =
            "Username: $username," +
                    " \nName: $name," +
                    " \nCompany: :$company," +
                    " \nLocation: $location," +
                    " \nRepository: $repository"
        tvObject.text = text

 */
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}