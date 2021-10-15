package id.ilmudata.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var actPlus: Boolean = false;
    var actMultiply: Boolean = false;
    var actSubract: Boolean = false;
    var actDivide: Boolean = false;
    var valueOne: Float = 0f;
    var valueTwo: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(v: View){
        when(v.id) {
            R.id.btn0 -> txtResult.setText(txtResult.text.toString() + "0")
            R.id.btn1 -> txtResult.setText(txtResult.text.toString() + "1")
            R.id.btn2 -> txtResult.setText(txtResult.text.toString() + "2")
            R.id.btn3 -> txtResult.setText(txtResult.text.toString() + "3")
            R.id.btn4 -> txtResult.setText(txtResult.text.toString() + "4")
            R.id.btn5 -> txtResult.setText(txtResult.text.toString() + "5")
            R.id.btn6 -> txtResult.setText(txtResult.text.toString() + "6")
            R.id.btn7 -> txtResult.setText(txtResult.text.toString() + "7")
            R.id.btn8 -> txtResult.setText(txtResult.text.toString() + "8")
            R.id.btn9 -> txtResult.setText(txtResult.text.toString() + "9")
            R.id.btnC -> txtResult.setText("")
            R.id.btnD -> txtResult.setText(txtResult.text.toString() + ".")
            R.id.btnPlus -> {
                valueOne = txtResult.text.toString().toFloat();
                actPlus = true;
                txtCache.setText(valueOne.toString());
                txtResult.setText(null);
            }
            R.id.btnDivide -> {
                valueOne = txtResult.text.toString().toFloat();
                actDivide = true;
                txtCache.setText(valueOne.toString());
                txtResult.setText(null);
            }
            R.id.btnMultiply -> {
                valueOne = txtResult.text.toString().toFloat();
                actMultiply = true;
                txtCache.setText(valueOne.toString());
                txtResult.setText(null);
            }
            R.id.btnSubtract -> {
                valueOne = txtResult.text.toString().toFloat();
                actSubract = true;
                txtCache.setText(valueOne.toString());
                txtResult.setText(null);
            }
            R.id.btnEqual -> {
                valueTwo = txtResult.text.toString().toFloat();
                if(actPlus){
                    txtResult.setText((valueOne + valueTwo).toString() + "");
                    txtCache.setText(null);
                    actPlus = false;
                }
                if(actDivide){
                    txtResult.setText((valueOne / valueTwo).toString() + "");
                    txtCache.setText(null);
                    actDivide = false;
                }
                if(actMultiply){
                    txtResult.setText((valueOne * valueTwo).toString() + "");
                    txtCache.setText(null);
                    actMultiply = false;
                }
                if(actSubract){
                    txtResult.setText((valueOne - valueTwo).toString() + "");
                    txtCache.setText(null);
                    actSubract = false;
                }
            }
        }
    }
}
