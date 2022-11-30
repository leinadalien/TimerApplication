package com.ldnprod.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ldnprod.timer.Adapters.TrainingAdapter
import com.ldnprod.timer.Utils.TrainingListEvent
import com.ldnprod.timer.ViewModels.TrainingListViewModel.TrainingListViewModelEvent
import com.ldnprod.timer.ViewModels.TrainingListViewModel.TrainingListViewModel
import com.ldnprod.timer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<TrainingListViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TrainingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = TrainingAdapter(this, viewModel.trainings)
        binding.apply {
            createButton.setOnClickListener {
                val intent = Intent(this@MainActivity, TrainingInfoActivity::class.java)
                startActivity(intent)
            }

            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }
        lifecycleScope.launch {
            viewModel.viewModelEvent.collect { event ->
                when(event) {
                    is TrainingListViewModelEvent.TrainingInserted -> {
                        adapter.notifyItemInserted(event.position)
                    }
                    is TrainingListViewModelEvent.TrainingSetChanged -> {
                        adapter.trainings = event.trainings
                        adapter.notifyDataSetChanged()
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onEvent(TrainingListEvent.OnRequestUpdatesForList)

    }
}