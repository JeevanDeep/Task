package jeevan.com.task.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import jeevan.com.task.model.Worldpopulation
import kotlinx.android.synthetic.main.item_grid_image.view.*

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val flagImage = itemView.ivFlag
    val country = itemView.tvCountry
    val population = itemView.tvPopulation

    fun setItems(currentModel: Worldpopulation) {
        Picasso
                .get()
                .load(currentModel.flag)
                .into(flagImage)

        country.text = currentModel.country
        population.text = "Population: " + currentModel.population
    }

}