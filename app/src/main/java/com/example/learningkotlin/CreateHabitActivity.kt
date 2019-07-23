package com.example.learningkotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_create_habit.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {
    private val TAG = CreateHabitActivity::class.java.simpleName
    private val chooseImageFile = 1
    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }
    fun storeHabit(view: View){
        if (et_title.isBlank() || et_desc.isBlank()){
            Log.d(TAG,"No habit stored: title or description missing")
            displayErrorMessage("Your habit needs an engaging title and description ")
            return
        }else if (imageBitmap == null){
            Log.d(TAG,"No habit stored: image missing")
            displayErrorMessage("Add a motivating picture to your habit")
            return
        }
        //Store the habit
        tv_error.visibility = View.INVISIBLE
    }
    private fun displayErrorMessage(message: String){
        tv_error.text = message
        tv_error.visibility = View.VISIBLE
    }
    fun chooseImage(view: View){
        val intent= Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Choose image for habit")
        startActivityForResult(chooser,chooseImageFile)

        Log.d(TAG, "Intent to choose image sent...")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == chooseImageFile && resultCode == Activity.RESULT_OK
            && data != null && data.data != null){
            Log.d(TAG, "An image was chosen by the user")

            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val bitmap = tryReadBitmap(data.data)

            bitmap?.let {
                this.imageBitmap = bitmap
                iv_image.setImageBitmap(bitmap)
                Log.d(TAG, "Read image bitmap  and update image view.")
            }
        }
    }

    private fun tryReadBitmap(data: Uri): Bitmap? {
        return try{
            MediaStore.Images.Media.getBitmap(contentResolver, data)
        }catch (e: IOException){
            e.printStackTrace()
            null
        }
    }
}
//Extension function for "EditText"
private fun EditText.isBlank() = this.text.toString().isBlank()
