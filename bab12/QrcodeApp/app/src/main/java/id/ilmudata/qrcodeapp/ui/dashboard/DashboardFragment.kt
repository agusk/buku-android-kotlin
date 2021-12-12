package id.ilmudata.qrcodeapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ilmudata.qrcodeapp.R

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.util.Log

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

import id.ilmudata.qrcodeapp.databinding.FragmentDashboardBinding

private const val TAG = "MainActivity"

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val text =  binding.editText.text.toString()
            if (text.isNotBlank()) {
                val bitmap = generateQRCode(text)
                binding.imageView.setImageBitmap(bitmap)
            }
        }

        return binding.root
    }

    private fun generateQRCode(text: String): Bitmap {
        val width = 500
        val height = 500
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) { Log.d(TAG, "generateQRCode: ${e.message}") }
        return bitmap
    }
}