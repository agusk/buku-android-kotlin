package id.ilmudata.qrcodeapp.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.ResultPointCallback
import id.ilmudata.qrcodeapp.R


import id.ilmudata.qrcodeapp.databinding.FragmentHomeBinding
class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null

//    private lateinit var btnLaunch: Button
//    private lateinit var tvResult: TextView
    internal var qrScanIntegrator: IntentIntegrator? = null

    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //val root = inflater.inflate(R.layout.fragment_home, container, false)

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        btnLaunch = view.findViewById(R.id.btnLaunch)
//        tvResult = view.findViewById(R.id.tvResult)

        binding.btnLaunch.setOnClickListener { scanBarcode() }

        qrScanIntegrator = IntentIntegrator.forSupportFragment(this)
        qrScanIntegrator?.setOrientationLocked(false)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun scanBarcode() {
        //qrScanIntegrator?.initiateScan()
        getResult.launch(qrScanIntegrator?.createScanIntent())
    }

    @SuppressLint("NotifyDataSetChanged")
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if(it.resultCode == Activity.RESULT_OK){
                val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                if (result != null) {
                    if (result.contents == null) {
                        Toast.makeText(this.context, "Scan cancelled", Toast.LENGTH_LONG).show()
                    } else {
                        binding.tvResult.text = result.getContents()
                        Toast.makeText(this.context, "Barcode: ${result.getContents()}", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this.context, "Scan cancelled", Toast.LENGTH_LONG).show()
                }
            }
        }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//        if (result != null) {
//            if (result.contents == null) {
//                Toast.makeText(this.context, "Scan cancelled", Toast.LENGTH_LONG).show()
//            } else {
//                binding.tvResult.text = result.getContents()
//                Toast.makeText(this.context, "Barcode: ${result.getContents()}", Toast.LENGTH_LONG).show()
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//    }

}