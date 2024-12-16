package com.example.onlineshoppro.Activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlineshoppro.Adapter.PicAdapter
import com.example.onlineshoppro.Adapter.SelectModelAdapter
import com.example.onlineshoppro.Model.ItemsModel
import com.example.onlineshoppro.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : BaseActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()
        initList()


    }

    private fun initList() {
        val modelList = ArrayList<String>()
        for (models in item.model) {
            modelList.add(models)
        }

        binding.modelList.adapter = SelectModelAdapter(modelList)
        binding.modelList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val picList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            picList.add(imageUrl)
        }

        Glide.with(this).load(picList).into(binding.img)

        binding.picList.adapter = PicAdapter(picList) { selectedImageUrl ->
            Glide.with(this).load(selectedImageUrl).into(binding.img)
        }

        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getBundle() {

        item = intent.getParcelableExtra("object")!!

        binding.titleText.text = item.title
        binding.descriptionText.text = item.description
        binding.priceText.text = "$" + item.price
        binding.ratingText.text = "${item.rating} Rating"
        binding.addToCartButton.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertItem(item)
        }
        binding.backButton.setOnClickListener { finish() }
        binding.cartButton.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java ))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}