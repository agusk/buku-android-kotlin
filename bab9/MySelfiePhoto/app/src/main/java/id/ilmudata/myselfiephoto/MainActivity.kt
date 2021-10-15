package id.ilmudata.myselfiephoto

import android.R.attr.bitmap
import android.R.attr.mimeType
import android.content.*
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun takePicture(v: View) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
            Log.e("MY_SELFIE_", e.message)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val img: ImageView = findViewById<ImageView>(R.id.imgPhoto)
            img.setImageBitmap(imageBitmap)
        }
    }

    fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun savePicture(v: View) {
        val imageview: ImageView = findViewById<ImageView>(R.id.imgPhoto)
        val b: Bitmap = (imageview.getDrawable() as BitmapDrawable).bitmap

        val cw = ContextWrapper(applicationContext)
        val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val file = File(directory, "img_" + getRandomString(5) + ".jpg")
        if (!file.exists()) {
            Log.d("MY_SELFIE_", file.toString())
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(file)
                b.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        Toast.makeText(this,"Gambar telah disimpan", Toast.LENGTH_LONG).show()
    }
}