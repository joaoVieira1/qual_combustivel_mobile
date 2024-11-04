package br.edu.ifsp.dmo1.qualcombustvel

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.qualcombustvel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClick()
    }

    private fun setClick(){
        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view == binding.buttonCalculate){
            calculate()
        }
    }

    private fun calculate(){
        if(binding.edittextGasoline.text.isEmpty() || binding.edittextEthanol.text.isEmpty()){
            Toast.makeText(this, getString(R.string.input_error), Toast.LENGTH_SHORT).show()
            binding.textviewResponse.text = ""
        }else{
            val gasoline = retriveValue(binding.edittextGasoline)
            val ethanol = retriveValue(binding.edittextEthanol)

            val result = ethanol / gasoline

            if(result < 0.7){
                binding.textviewResponse.text = getString(R.string.answer_ethanol)
                binding.textviewResponse.setTextColor(resources.getColor(R.color.ethanol, this.theme))
            }else{
                binding.textviewResponse.text = getString(R.string.answer_gasoline)
                binding.textviewResponse.setTextColor(resources.getColor(R.color.gasoline, this.theme))
            }
        }
    }

    private fun retriveValue(input: EditText): Double{
        return try{
            input.text.toString().toDouble()
        }catch (e: NumberFormatException){
            Toast.makeText(this, getString(R.string.number_error), Toast.LENGTH_SHORT).show()
            0.0
        }
    }

}