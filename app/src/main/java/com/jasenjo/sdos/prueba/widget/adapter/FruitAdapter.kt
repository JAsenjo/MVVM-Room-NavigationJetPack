package com.jasenjo.sdos.prueba.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jasenjo.sdos.R
import com.jasenjo.sdos.databinding.ItemFruitBinding
import com.jasenjo.sdos.prueba.persistence.entity.FruitEntity

class FruitAdapter(private val fruits: Array<FruitEntity>?): RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        return FruitViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_fruit, parent, false))
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.fruitBinding.fruit = fruits?.get(position)
    }

    override fun getItemCount(): Int {
        return fruits?.size?: 0
    }

    inner class FruitViewHolder(val fruitBinding: ItemFruitBinding) :
        RecyclerView.ViewHolder(fruitBinding.root)

}