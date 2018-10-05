package jeevan.com.task.activity.imageTask

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jeevan.com.task.R
import jeevan.com.task.adapter.ImageAdapter
import jeevan.com.task.model.WorldPopulationModel
import jeevan.com.task.utils.ApiInterface
import jeevan.com.task.utils.OnItemClickListener
import jeevan.com.task.utils.addOnItemClickListener
import kotlinx.android.synthetic.main.activity_images_list.*
import org.jetbrains.anko.indeterminateProgressDialog


class ImagesListActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images_list)

        apiCallForGettingImages()
    }

    private fun apiCallForGettingImages() {

        val dialog = indeterminateProgressDialog(message = "Please wait a bit…", title = "Fetching data")

        disposable = ApiInterface.getClient().getImages()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    initAdapter(it)
                }, {
                    it.printStackTrace()
                }, {
                    dialog.dismiss()
                })
    }

    private fun initAdapter(model: WorldPopulationModel) {
        val imageAdapter = ImageAdapter(this, model.worldpopulation)

        recyclerview.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context)
        }

        recyclerview.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(this@ImagesListActivity, FullScreenImageActivity::class.java)
                val link = imageAdapter.getItemAtPosition(position).flag

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@ImagesListActivity,
                        view.findViewById(R.id.ivFlag),
                        ViewCompat.getTransitionName(view.findViewById(R.id.ivFlag)) ?: "")


                intent.putExtra("image", link)
                startActivity(intent, options.toBundle())
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposable!=null)
            disposable?.dispose()
    }
}
