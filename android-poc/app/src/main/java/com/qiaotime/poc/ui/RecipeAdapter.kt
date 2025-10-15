package com.qiaotime.poc.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qiaotime.poc.R
import com.qiaotime.poc.data.models.Recipe

class RecipeAdapter(
    private val onFavToggle: (recipe: Recipe, isFav: Boolean) -> Unit,
    private val onClick: (recipe: Recipe) -> Unit
) : ListAdapter<Recipe, RecipeAdapter.VH>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val r = getItem(position)
        holder.bind(r)
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val favBtn: ImageButton = view.findViewById(R.id.favBtn)

        fun bind(r: Recipe) {
            title.text = r.title
            // default icon; caller should manage state via onFavToggle
            favBtn.setOnClickListener {
                // toggle UI state optimistically
                val isNow = favBtn.tag != true
                favBtn.tag = isNow
                favBtn.setImageResource(if (isNow) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
                onFavToggle(r, isNow)
            }

            title.setOnClickListener { onClick(r) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean = oldItem == newItem
        }
    }
}
