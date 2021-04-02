package com.example.submission2

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter
        binding.rvUser.setHasFixedSize(true)
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

    /*
    fun setUser(username: String) {
        val listItems = ArrayList<UserItems>()
        val client = AsyncHttpClient()
        val url = "https://api.github.com/search/users?q=${username}"
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
               val listUser = ArrayList<UserItems>()
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    //parsing json
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("items")
                    for (i in 0 until items.length()) {
                        val item = items.getJSONObject(i)
                        val username = item.getString("login")
                        val avatar = item.getString("avatar_url")
                        val user = UserItems()
                        user.username = username
                        user.avatar = avatar
                        listUser.add(user)
                    }
                    //set data ke adapter
                    adapter.setData(listItems)
                    showLoading(false)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }
   }
     */
