package id.ilmudata.myselfiephoto

import android.annotation.SuppressLint
import android.content.*
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

import id.ilmudata.myselfiephoto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun takePicture(view: View) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            getResult.launch(takePictureIntent)
        } catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("MY_SELFIE_", it) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){

            if (it.resultCode == RESULT_OK) {
                val imageBitmap = it.data?.extras?.get("data") as Bitmap
                binding.imgPhoto.setImageBitmap(imageBitmap)
            }
        }

    private fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun savePicture(view: View) {
        val b: Bitmap = (binding.imgPhoto.getDrawable() as BitmapDrawable).bitmap

        val cw = ContextWrapper(applicationContext)
        val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val file = File(directory, "img_" + getRandomString(5) + ".jpg")
        if (!file.exists()) {
            Log.d("MY_SELFIE_", file.toString())
            try {
                val fos = FileOutputStream(file)
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