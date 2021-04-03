package com.example.submission2

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission2.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private var list: ArrayList<UserItems> =  arrayListOf()

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        adapter.notifyDataSetChanged()
        adapter = UserAdapter()

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter

        setDataToAdapter()

        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserItems) {
                showSelectedUser(data)
            }
        })
    }

    private fun setDataToAdapter(){
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.getUsers().observe(this, { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
                showLoading(false)
            }
        })
    }

    private fun showSelectedUser(user: UserItems) {
        val moveIntent = Intent(this@MainActivity, HalamanDetail::class.java).apply {
            putExtra(HalamanDetail.EXTRA_USER, user)
        }
        startActivity(moveIntent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}

