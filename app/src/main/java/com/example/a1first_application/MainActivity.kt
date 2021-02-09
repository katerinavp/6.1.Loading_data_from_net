package com.example.a1first_application

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1first_application.databinding.ActivityMainBinding
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val url = "https://raw.githubusercontent.com/katerinavp/GSON/master/posts.json"
    lateinit var adapter: AdapterPost
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterPost()
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            getResultFromGit()
        }

    }

    private suspend fun getResultFromGit() {
        val client = HttpClient {
            install(JsonFeature) {
                acceptContentTypes = listOf(
                        ContentType.Text.Plain,
                        ContentType.Application.Json
                )
                serializer = GsonSerializer()
            }
        }
        // тестовый ответ будет десериализован в List<Post>
        val response = client.get<List<Post>>(url)
        println("Десериализация + ${response}")
        client.close()
        setResponse(response)
    }

    private fun setResponse(response: List<Post>) {
        adapter.submitList(response)

    }

}



















