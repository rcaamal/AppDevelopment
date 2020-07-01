package com.classwork.apiassignment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.Collections.addAll

class TvShowAdapter (
        private var tvShow: MutableList<TvShow>,
        private val onTvShowClick: (tvshow: TvShow) -> Unit,
        private val myshowsLiked: MutableList<Like>
        ) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {


            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
                val view = LayoutInflater
                    .from(parent.context)
                .inflate(R.layout.item_tv_show, parent, false)

                return TvShowViewHolder(view)
            }

            override fun getItemCount(): Int = tvShow.size

        override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
                holder.bind(tvShow[position])
            }
            fun appendTvShows(tvShow: List<TvShow>) {
                this.tvShow.addAll(tvShow)
                notifyItemRangeInserted(
                this.tvShow.size,
                tvShow.size -1)
            }
            inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                private val poster: ImageView = itemView.findViewById(R.id.item_tv_show_poster)
                fun bind(tvshow: TvShow) {
                    val toggleButton = itemView.findViewById<ToggleButton>(R.id.button_favorite)

                    toggleButton.setOnCheckedChangeListener{buttonView, isChecked ->

                        val user = FirebaseAuth.getInstance().currentUser
                        val tvShowLikes = Like(null, user!!.uid,tvshow.id.toString())
                        val database =  FirebaseDatabase.getInstance()

                        if (isChecked){
                            var exists = false
                            myshowsLiked.forEach{
                                if(it.tvShowId == tvshow.id.toString()){
                                    exists = true
                                }
                            }

                            if (!exists){
                                val newLikeReference = database.reference.child("Users").push().key
                                tvShowLikes.key = newLikeReference
                                database.reference.child("Users").child(newLikeReference.toString()).setValue(tvShowLikes)
                                myshowsLiked.add(tvShowLikes)

                            }

                        }
                        else{
                            myshowsLiked.forEach {
                                if(it.tvShowId == tvshow.id.toString()){

                                 tvShowLikes.key  = it.key.toString()
                                 database.reference.child("Users").child(it.key.toString()).removeValue()

                                 myshowsLiked.removeIf { favorites -> favorites.key.equals(it.key.toString()) }

                                }
                            }

                        }

                    }

                    var favorites = false
                    myshowsLiked?.forEach{
                        if(tvshow.id.toString() == it.tvShowId){
                            favorites = true
                        }
                    }

                    toggleButton.isChecked = favorites

                    Glide.with(itemView)
                        .load("https://image.tmdb.org/t/p/w342${tvshow.posterPath}") .transform(CenterCrop())
                        .into(poster)
                    itemView.setOnClickListener { onTvShowClick.invoke(tvshow) }


                }

            }



        }