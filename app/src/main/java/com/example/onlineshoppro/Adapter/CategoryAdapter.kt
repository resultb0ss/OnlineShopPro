package com.example.onlineshoppro.Adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshoppro.Activity.ListItemsActivity
import com.example.onlineshoppro.Model.CategoryModel
import com.example.onlineshoppro.R
import com.example.onlineshoppro.databinding.ViewHolderCategoryBinding

class CategoryAdapter(val items: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding =
            ViewHolderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.binding.titleText.text = item.title
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.green_button_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, R.color.white)
                )
            )
            holder.binding.titleText.visibility = View.VISIBLE
            holder.binding.titleText.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, R.color.black)
                )
            )
            holder.binding.titleText.visibility = View.GONE
            holder.binding.titleText.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )
        }

        holder.binding.root.setOnClickListener {
            val position = position
            if (position != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)

            }
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(holder.itemView.context, ListItemsActivity::class.java).apply {
                    putExtra("id", item.id.toString())
                    putExtra("title", item.title)
                }
                ContextCompat.startActivity(holder.itemView.context, intent,null)
            }, 1000)
        }
    }

    override fun getItemCount() = items.size

    inner class CategoryViewHolder(val binding: ViewHolderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}