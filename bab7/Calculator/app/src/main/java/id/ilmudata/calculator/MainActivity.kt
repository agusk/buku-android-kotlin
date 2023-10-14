package id.ilmudata.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.ilmudata.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var actPlus: Boolean = false
    private var actMultiply: Boolean = false
    private var actSubtract: Boolean = false
    private var actDivide: Boolean = false
    private var valueOne: Float = 0f
    private var valueTwo: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun calculate(v: View){
        var text = binding.txtResult.text.toString()
        if(text=="0"){
            text = ""
        }
        when(v.id) {
            R.id.btn0 -> binding.txtResult.text = text.plus("0")
            R.id.btn1 -> binding.txtResult.text = text.plus("1")
            R.id.btn2 -> binding.txtResult.text = text.plus("2")
            R.id.btn3 -> binding.txtResult.text = text.plus( "3")
            R.id.btn4 -> binding.txtResult.text = text.plus("4")
            R.id.btn5 -> binding.txtResult.text = text.plus("5")
            R.id.btn6 -> binding.txtResult.text = text.plus("6")
            R.id.btn7 -> binding.txtResult.text = text.plus("7")
            R.id.btn8 -> binding.txtResult.text = text.plus("8")
            R.id.btn9 -> binding.txtResult.text = text.plus("9")
            R.id.btnC -> binding.txtResult.text = ""
            R.id.btnD -> binding.txtResult.text = text.plus(".")
            R.id.btnPlus -> {
                valueOne = binding.txtResult.text.toString().toFloat()
                actPlus = true
                binding.txtCache.text = valueOne.toString()
                binding.txtResult.text = "0"
            }
            R.id.btnDivide -> {
                valueOne = binding.txtResult.text.toString().toFloat()
                actDivide = true
                binding.txtCache.text = valueOne.toString()
                binding.txtResult.text = "0"
            }
            R.id.btnMultiply -> {
                valueOne = binding.txtResult.text.toString().toFloat()
                actMultiply = true
                binding.txtCache.text = valueOne.toString()
                binding.txtResult.text = "0"
            }
            R.id.btnSubtract -> {
                valueOne = binding.txtResult.text.toString().toFloat()
                actSubtract = true
                binding.txtCache.text = valueOne.toString()
                binding.txtResult.text = "0"
            }
            R.id.btnEqual -> {
                valueTwo = binding.txtResult.text.toString().toFloat()
                if(actPlus){
                    val result : String = "${(valueOne + valueTwo)}"
                    binding.txtResult.text = result
                    binding.txtCache.text = ""
                    actPlus = false
                }
                if(actDivide){
                    val result : String = "${(valueOne / valueTwo)}"
                    binding.txtResult.text = result
                    binding.txtCache.text = ""
                    actDivide = false
                }
                if(actMultiply){
                    val result : String = "${(valueOne * valueTwo)}"
                    binding.txtResult.text = result
                    binding.txtCache.text = ""
                    actMultiply = false
                }
                if(actSubtract){
                    val result : String = "${(valueOne - valueTwo)}"
                    binding.txtResult.text = result
                    binding.txtCache.text = ""
                    actSubtract = false
                }
            }
        }
    }
}
