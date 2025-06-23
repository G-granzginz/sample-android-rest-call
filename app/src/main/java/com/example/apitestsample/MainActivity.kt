package com.example.apitestsample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCallApi = findViewById<Button>(R.id.btnCallApi)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCallApi.setOnClickListener {
            tvResult.text = "Calling..."

            RetrofitClient.instance.getTest().enqueue(object : retrofit2.Callback<ApiResponse> {
                override fun onResponse(call: retrofit2.Call<ApiResponse>, response: retrofit2.Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        tvResult.text = "Status: ${data?.status}\nMessage: ${data?.message}\nTimestamp: ${data?.timestamp}"
                    } else {
                        tvResult.text = "❌ Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: retrofit2.Call<ApiResponse>, t: Throwable) {
                    tvResult.text = "⚠️ Failed: ${t.message}"
                }
            })
        }

    }
}
