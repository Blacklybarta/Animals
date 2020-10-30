package com.jefpoughon.animals.ui.animals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jefpoughon.animals.R
import com.jefpoughon.animals.model.AnimalPicture
import com.jefpoughon.animals.ui.BaseActivity
import kotlinx.android.synthetic.main.animal_row.view.*

class AnimalAdapter(private val animal: List<AnimalPicture>, private val activity: BaseActivity) :
    RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.animal_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(animal[position], activity)

    override fun getItemCount() = animal.size

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AnimalPicture, activity: BaseActivity) = with(itemView) {
            Glide.with(activity)
                .load(if (item.file.isNotEmpty()) item.file else item.url)
                .circleCrop()
                .into(animal_image)
            animal_image.setOnClickListener {
                activity.saveAsFavorite(item)
            }
        }
    }
}