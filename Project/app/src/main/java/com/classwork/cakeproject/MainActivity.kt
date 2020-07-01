package com.classwork.cakeproject
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.classwork.cakeproject.ui.main.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var btnLogOut: Button? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        terminate()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val camera: FloatingActionButton = findViewById(R.id.fabUpload)
        val image: FloatingActionButton = findViewById(R.id.fabImages)
        val maps: FloatingActionButton = findViewById(R.id.fabMap)

        fab.setOnClickListener{ view ->
            startActivity(Intent(this, OrderPageActivity::class.java)) }

        camera.setOnClickListener{view ->
            startActivity(Intent(this, UploadClass::class.java))
        }

        image.setOnClickListener{view ->
            startActivity(Intent(this, ImageActivity::class.java))
        }
        maps.setOnClickListener { view ->
            startActivity(Intent(this, MapsActivity::class.java))
        }


    }


    private fun terminate()
    {
        btnLogOut = findViewById<View>(R.id.logOutButton) as Button
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



}