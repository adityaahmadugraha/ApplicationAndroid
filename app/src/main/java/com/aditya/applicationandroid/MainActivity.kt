package com.aditya.applicationandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.applicationandroid.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: InfoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this@MainActivity, ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        mAdapter = InfoAdapter()

        viewModel.posts.observe(this@MainActivity) { listPosts ->
            mAdapter.submitList(listPosts)
            binding.rvListposts.adapter = mAdapter
            binding.rvListposts.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvListposts.setHasFixedSize(true)
        }

        viewModel.isLoading.observe(this@MainActivity) {  isloading ->

            binding.progressBar.isVisible = isloading
        }
    }


}