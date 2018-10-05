package jeevan.com.task.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jeevan.com.task.R
import jeevan.com.task.model.Worldpopulation
import jeevan.com.task.viewholder.ImageViewHolder

class ImageAdapter(var context: Context, var worldPopulationList: MutableList<Worldpopulation>) : RecyclerView.Adapter<ImageViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    fun getItemAtPosition(position: Int): Worldpopulation {
        return worldPopulationList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val v = layoutInflater.inflate(R.layout.item_grid_image, parent, false)
        return ImageViewHolder(v)
    }

    override fun getItemCount(): Int {
        return worldPopulationList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentModel = worldPopulationList[position]
        holder.setItems(currentModel)
    }


}