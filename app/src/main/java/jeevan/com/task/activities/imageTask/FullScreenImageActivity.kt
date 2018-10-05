package jeevan.com.task.activities.imageTask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import jeevan.com.task.R
import kotlinx.android.synthetic.main.activity_full_screen_image.*

class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        supportActionBar!!.hide()
        val link = intent.getStringExtra("image")

        Picasso.get()
                .load(link)
                .into(imageView)

        closeImage.setOnClickListener {
            supportFinishAfterTransition()
        }

    }
}
