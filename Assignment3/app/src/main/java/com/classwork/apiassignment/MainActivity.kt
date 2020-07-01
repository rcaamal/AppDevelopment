package com.classwork.apiassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var popularTvShows: RecyclerView
    private lateinit var popularTvShowsAdapter: TvShowAdapter
    private lateinit var popularTvShowsLayoutMgr: LinearLayoutManager

    private lateinit var topRatedTvShows: RecyclerView
    private lateinit var topRatedTvShowsAdapter: TvShowAdapter
    private lateinit var topRatedTvShowsLayoutMgr: LinearLayoutManager

    private lateinit var onAirTvShows: RecyclerView
    private lateinit var onAirTvShowsAdapter: TvShowAdapter
    private lateinit var onAirTvShowsLayoutMgr: LinearLayoutManager

    private lateinit var airtodayTvShows: RecyclerView
    private lateinit var airtodayTvShowsAdapter: TvShowAdapter
    private lateinit var airtodayTvShowsLayoutMgr: LinearLayoutManager

    private var popularTvShowsPage = 1
    private var topRatedTvShowsPage = 1
    private var onAirTvShowsPage = 1
    private var airtodayTvShowsPage = 1

    private var btnLogOut: Button? = null
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        terminate()


        val mySnapshot = ArrayList<Like>()
        val database = FirebaseDatabase.getInstance().reference

        val userListener = database.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (like in snapshot.children){
                    if(like.key == "Users"){
                        for(showsLiked in like.children){
                            val popularShows = showsLiked.getValue(Like::class.java!!)
                            mySnapshot.add(popularShows!!)
                        }
                    }
                }

            }

        })

        popularTvShows = findViewById(R.id.popular_tv_shows)
        popularTvShowsLayoutMgr = LinearLayoutManager(

            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        topRatedTvShows = findViewById(R.id.top_rated_Tv_shows)
        topRatedTvShowsLayoutMgr = LinearLayoutManager(

            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        onAirTvShows = findViewById(R.id.on_tv_Tv_shows)
        onAirTvShowsLayoutMgr = LinearLayoutManager(

            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        airtodayTvShows = findViewById(R.id.airing_today_Tv_shows)
        airtodayTvShowsLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )




        popularTvShows.layoutManager = popularTvShowsLayoutMgr


        popularTvShowsAdapter = TvShowAdapter(mutableListOf(), { tvshow -> showTvShowDetails(tvshow)}, mySnapshot)
        popularTvShows.adapter = popularTvShowsAdapter

        topRatedTvShows.layoutManager = topRatedTvShowsLayoutMgr
        topRatedTvShowsAdapter = TvShowAdapter(mutableListOf(), { tvshow -> showTvShowDetails(tvshow) },mySnapshot)
        topRatedTvShows.adapter = topRatedTvShowsAdapter

        onAirTvShows.layoutManager = onAirTvShowsLayoutMgr
        onAirTvShowsAdapter = TvShowAdapter(mutableListOf(), { tvshow -> showTvShowDetails(tvshow) },mySnapshot)
        onAirTvShows.adapter = onAirTvShowsAdapter

        airtodayTvShows.layoutManager = airtodayTvShowsLayoutMgr
        airtodayTvShowsAdapter= TvShowAdapter(mutableListOf(), { tvshow -> showTvShowDetails(tvshow) },mySnapshot)
        airtodayTvShows.adapter = airtodayTvShowsAdapter

        getPopularTvShows()
        getTopRatedTvShows()
        getOnAirTvShows()
        getAiringTodayTvShows()
    }


    private fun terminate()
    {
        btnLogOut = findViewById<View>(R.id.logoutbutton) as Button
        btnLogOut!!.setOnClickListener { logoutUser() }
        mAuth = FirebaseAuth.getInstance()
    }

    private fun logoutUser()
    {
        mAuth!!.signOut()
        updateUI()

    }

    private fun updateUI() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }



        private fun getPopularTvShows() {
            TvShowRepository.getPopularTvShows(
                popularTvShowsPage,
                onSuccess = ::onPopularTvShowsFetched,
                onError = ::onError
            )
        }

    private fun getTopRatedTvShows(){

        TvShowRepository.getTopRatedTvShows(
            topRatedTvShowsPage,
            onSuccess = ::onTopRatedTvShowsFetched,
            onError = ::onError
        )

    }

    private fun getOnAirTvShows(){

        TvShowRepository.geOnAirTvShows(
            onAirTvShowsPage,
            onSuccess = ::onOnAirTvShowsFetched,
            onError = ::onError
        )

    }

    private fun getAiringTodayTvShows() {
        TvShowRepository.getAiringTodayTvShows(
            airtodayTvShowsPage,
            ::onAirTodayTvShowsFetched,
            ::onError
        ) }

    private fun onPopularTvShowsFetched(tvShow: List<TvShow>) {
        popularTvShowsAdapter.appendTvShows(tvShow)
        attachPopularTvShowsOnScrollListener()
    }

    private fun attachPopularTvShowsOnScrollListener(){
        popularTvShows.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView,dx:Int, dy: Int){
                val totalItemCount = popularTvShowsLayoutMgr.itemCount
                val visibleItemCount = popularTvShowsLayoutMgr.childCount
                val firstVisbleItem = popularTvShowsLayoutMgr.findFirstCompletelyVisibleItemPosition()

                if(firstVisbleItem + visibleItemCount >= totalItemCount / 2){
                    popularTvShows.removeOnScrollListener(this)
                    popularTvShowsPage++
                    getPopularTvShows()
                }
            }
        })
    }


    private fun attachTopRatedTvShowsOnScrollListener(){
        topRatedTvShows.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView,dx:Int, dy: Int){
                val totalItemCount = topRatedTvShowsLayoutMgr.itemCount
                val visibleItemCount = topRatedTvShowsLayoutMgr.childCount
                val firstVisbleItem = topRatedTvShowsLayoutMgr.findFirstCompletelyVisibleItemPosition()

                if(firstVisbleItem + visibleItemCount >= totalItemCount / 2){
                    topRatedTvShows.removeOnScrollListener(this)
                    topRatedTvShowsPage++
                    getTopRatedTvShows()
                }
            }
        })
    }

    private fun onTopRatedTvShowsFetched(tvShow: List<TvShow>) {
        topRatedTvShowsAdapter.appendTvShows(tvShow)
        attachTopRatedTvShowsOnScrollListener()
    }

    private fun attachOnAirTvShowsOnScrollListener(){
        onAirTvShows.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView,dx:Int, dy: Int){
                val totalItemCount = onAirTvShowsLayoutMgr.itemCount
                val visibleItemCount = onAirTvShowsLayoutMgr.childCount
                val firstVisbleItem = onAirTvShowsLayoutMgr.findFirstCompletelyVisibleItemPosition()

                if(firstVisbleItem + visibleItemCount >= totalItemCount / 2){
                    topRatedTvShows.removeOnScrollListener(this)
                    topRatedTvShowsPage++
                    getTopRatedTvShows()
                }
            }
        })
    }

    private fun onOnAirTvShowsFetched(tvShow: List<TvShow>) {
        onAirTvShowsAdapter.appendTvShows(tvShow)
        attachOnAirTvShowsOnScrollListener()
    }


    private fun attachAirTodayTvShowsOnScrollListener(){
        airtodayTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = airtodayTvShowsLayoutMgr.itemCount
                val visibleItemCount = airtodayTvShowsLayoutMgr.childCount
                val firstVisibleItem = airtodayTvShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    airtodayTvShows.removeOnScrollListener(this)
                    airtodayTvShowsPage++
                    getAiringTodayTvShows()
                }
            }
        })
    }

    private  fun onAirTodayTvShowsFetched(tvShow: List<TvShow>) {
        airtodayTvShowsAdapter.appendTvShows(tvShow)
        attachAirTodayTvShowsOnScrollListener()
    }


    private fun showTvShowDetails(tvshow: TvShow) {
        val intent = Intent(this, TvShowsDetailsActivity::class.java)
        intent.putExtra(TV_SHOW_BACKDROP, tvshow.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, tvshow.posterPath)
        intent.putExtra(TV_SHOW_TITLE, tvshow.title)
        intent.putExtra(TV_SHOW_RATING, tvshow.rating)
        intent.putExtra(TV_SHOW_EPISODE_COUNT, tvshow.episodeCount)
        intent.putExtra(TV_SHOW_OVERVIEW, tvshow.overview)
        startActivity(intent)
    }


    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_tv_shows), Toast.LENGTH_SHORT).show()
    }






}