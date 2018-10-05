package jeevan.com.task.activity

import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jeevan.com.task.R
import jeevan.com.task.model.ContactModel
import jeevan.com.task.utils.CsvWriter
import kotlinx.android.synthetic.main.activity_contact_list.*
import org.jetbrains.anko.indeterminateProgressDialog
import java.io.File

class ContactListActivity : AppCompatActivity() {

    private lateinit var list: ArrayList<ContactModel>
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        list = ArrayList()

        val dialog = indeterminateProgressDialog(message = "Please wait a bit…", title = "Zipping data")


        disposable = Observable.fromCallable {
            fetchAndSaveContacts()
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                }, {
                    dialog.dismiss()
                    Snackbar.make(rootView, "File zipped successfully", Snackbar.LENGTH_LONG).show()

                    result.text = "File saved successfully at ${Environment.getExternalStorageDirectory().toString() + "/MyFolder"}"
                }, {

                })
    }


    override fun onDestroy() {
        super.onDestroy()
        if (disposable != null)
            disposable?.dispose()
    }

    private fun fetchAndSaveContacts() {

        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)

        while (cursor?.moveToNext() == true) {

            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

            val phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            list.add(ContactModel(name, phonenumber))
        }

        cursor?.close()
        val folder = File("" + Environment.getExternalStorageDirectory() + "/MyFolder")


        if (!folder.exists())
            folder.mkdir()
        val fileName = folder.toString() + "/sample.csv"
        CsvWriter.saveToCsv(fileName, list)


    }
}
