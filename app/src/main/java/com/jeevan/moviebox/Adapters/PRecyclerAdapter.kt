package com.jeevan.moviebox.Adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jeevan.moviebox.Listeners.MainFragmentListener
import com.jeevan.moviebox.Model.Popular.Result
import com.jeevan.moviebox.R

class PRecyclerAdapter(private val dataSet : List<Result>, private val listener : MainFragmentListener) : RecyclerView.Adapter<PRecyclerAdapter.PRecyclerAdapterViewHolder>() {

    var data = dataSet
    val IMAGE_URL = "https://image.tmdb.org/t/p/w92"

    class PRecyclerAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var poster : ImageView
        var title : TextView
        var date : TextView
        var consLayout : ConstraintLayout

        init {
            poster = view.findViewById(R.id.PmoviePoster)
            title = view.findViewById(R.id.PTitle)
            date = view.findViewById(R.id.PDate)
            consLayout = view.findViewById(R.id.movie_item_layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PRecyclerAdapterViewHolder {
        return PRecyclerAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_data_item,parent, false))
    }

    fun refresh(dataReceived: List<Result>){
        data = dataReceived
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PRecyclerAdapterViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.date.text = data[position].release_date
        val options = RequestOptions.fitCenterTransform()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .encodeFormat(Bitmap.CompressFormat.PNG)
        Glide.with(holder.poster).load(IMAGE_URL + data[position].poster_path).apply(options).into(holder.poster)

        holder.consLayout.setOnClickListener{
            listener.getMoviesById(data[position])
        }

    }

    override fun getItemCount(): Int = data.size
}
