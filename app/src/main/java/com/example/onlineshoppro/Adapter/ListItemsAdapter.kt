package com.example.onlineshoppro.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshoppro.Activity.DetailActivity
import com.example.onlineshoppro.Model.ItemsModel
import com.example.onlineshoppro.databinding.ViewholderRecommendedBinding

class ListItemsAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<ListItemsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemsAdapter.ViewHolder {
        val binding =
            ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemsAdapter.ViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding) {
            titleText.text = item.title
            priceText.text = "$${item.price}"
            ratingText.text = item.rating.toString()

            Glide.with(holder.itemView.context).load(item.picUrl[0]).into(pic)

            root.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                    putExtra("object", item)
                }
                ContextCompat.startActivity(holder.itemView.context, intent, null)

            }
        }
    }

    override fun getItemCount() = items.size


}