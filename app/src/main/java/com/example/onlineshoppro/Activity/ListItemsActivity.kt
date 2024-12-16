package com.example.onlineshoppro.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshoppro.Adapter.ListItemsAdapter
import com.example.onlineshoppro.ViewModel.MainViewModel
import com.example.onlineshoppro.databinding.ActivityListItemsBinding

class ListItemsActivity : BaseActivity() {

    private var _binding: ActivityListItemsBinding? = null
    private val binding get() = _binding!!

    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList(){
        binding.apply {
            binding.backButton.setOnClickListener { finish() }
            progressBarList.visibility = View.VISIBLE
            viewModel.recommended.observe(this@ListItemsActivity, Observer {
                viewList.layoutManager = GridLayoutManager(this@ListItemsActivity, 2)
                viewList.adapter = ListItemsAdapter(it)
                progressBarList.visibility = View.GONE
            })
            viewModel.loadFiltered(id)
        }
    }

    private fun getBundle(){
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryText.text = title
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}