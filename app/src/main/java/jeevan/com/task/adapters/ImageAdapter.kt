package jeevan.com.task.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import jeevan.com.task.R
import jeevan.com.task.models.WorldPopulationModel
import jeevan.com.task.models.Worldpopulation

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    var context: Context
    var layoutInflater: LayoutInflater
    var model: WorldPopulationModel

    constructor(context: Context, model: WorldPopulationModel) {
        this.context = context
        this.model = model
        layoutInflater = LayoutInflater.from(context)
    }

    fun getItemAtPosition(position: Int): Worldpopulation {
        return model.worldpopulation[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val v = layoutInflater.inflate(R.layout.item_grid_image, parent, false)
        return ImageViewHolder(v)
    }

    override fun getItemCount(): Int {
        return model.worldpopulation.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentModel = model.worldpopulation[position]
        Picasso
                .get()
                .load(currentModel.flag)
                .into(holder.imageView)

        holder.country.text = currentModel.country
        holder.population.text = "Population: " + currentModel.population
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val country = itemView.findViewById<TextView>(R.id.country)
        val population = itemView.findViewById<TextView>(R.id.population)
    }
}