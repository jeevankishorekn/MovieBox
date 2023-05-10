package com.jeevan.moviebox.Adapters

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jeevan.moviebox.Model.NowPlaying.Result
import com.jeevan.moviebox.R

class NPRecyclerAdapter(private val data : List<Result>) : RecyclerView.Adapter<NPRecyclerAdapter.NPRecyclerAdapterViewHolder>() {

    val IMAGE_URL = "https://image.tmdb.org/t/p/w92"
    var dataSet = data
    class NPRecyclerAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var image : ImageView

        init {
            image = itemView.findViewById(R.id.NPImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NPRecyclerAdapterViewHolder {
        return NPRecyclerAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false))
    }

    override fun onBindViewHolder(holder: NPRecyclerAdapterViewHolder, position: Int) {

        Log.d(TAG, "onBindViewHolder:" + IMAGE_URL + dataSet[position].poster_path)
        val options = RequestOptions.fitCenterTransform()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .encodeFormat(Bitmap.CompressFormat.PNG)
        Glide.with(holder.image).load(IMAGE_URL + dataSet[position].poster_path).apply(options).into(holder.image)
    }

    override fun getItemCount(): Int = dataSet.size
    fun refresh(result: MutableList<Result>) {
        dataSet = result
        notifyDataSetChanged()
    }
}