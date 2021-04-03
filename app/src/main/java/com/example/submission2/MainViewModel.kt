package com.example.submission2

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    val listUsers = MutableLiveData<ArrayList<UserItems>>()

    fun setUser(username: String) {
        val listItems = ArrayList<UserItems>()

        val client = AsyncHttpClient()
        val apiKey = "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"
        val url = "https://api.github.com/search/users?q=${username}"
        client.addHeader("Authorization", "token $apiKey")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
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
                        user.avatar = avatar.toInt()
                        listItems.add(user)
                    }
                    listUsers.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    fun getUsers(): LiveData<ArrayList<UserItems>> {
        return listUsers
    }
}