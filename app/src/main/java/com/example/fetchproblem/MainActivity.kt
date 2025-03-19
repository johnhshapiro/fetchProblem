package com.example.fetchproblem

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproblem.adapter.HiringItemRecyclerViewAdapter
import com.example.fetchproblem.viewModel.MainActivityViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            viewModel.hiringViewState.collect { viewState ->
                val loadingProgressBar = findViewById<ContentLoadingProgressBar>(R.id.loadingProgressBar)
                loadingProgressBar.isVisible= viewState.loading

                val hiringItemRecyclerViewAdapter = HiringItemRecyclerViewAdapter(viewState.hiringItems)
                val recyclerView: RecyclerView = findViewById(R.id.hiringItemsRecyclerView)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = hiringItemRecyclerViewAdapter
            }
        }
    }
}