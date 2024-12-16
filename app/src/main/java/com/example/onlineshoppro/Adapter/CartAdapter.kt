package com.example.onlineshoppro.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshoppro.Model.ItemsModel
import com.example.onlineshoppro.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

        private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = listItemSelected[position]

        holder.binding.titleText.text = item.title
        holder.binding.feeEachTime.text = "$${item.price}"
        holder.binding.totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberItemText.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context).load(item.picUrl[0]).into(holder.binding.picCart)
        holder.binding.plusCartButton.setOnClickListener{
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }

            })
        }

        holder.binding.minusCartButton.setOnClickListener{
            managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int = listItemSelected.size


    class ViewHolder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}