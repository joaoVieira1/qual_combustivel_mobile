package br.edu.ifsp.dmo1.qualcombustvel

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var gasolineEditText: EditText
    private lateinit var ethanolEditText: EditText
    private lateinit var mButton: Button
    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findById()
        setClick()
    }

    private fun findById(){
        gasolineEditText = findViewById(R.id.edittext_gasoline)
        ethanolEditText = findViewById(R.id.edittext_ethanol)
        mButton = findViewById(R.id.button_calculate)
        mTextView = findViewById(R.id.textview_response)
    }

    private fun setClick(){
        mButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view == mButton){
            calculate()
        }
    }

    private fun calculate(){
        if(gasolineEditText.text.isEmpty() || ethanolEditText.text.isEmpty()){
            Toast.makeText(this, getString(R.string.input_error), Toast.LENGTH_SHORT).show()
            mTextView.text = ""
        }else{
            val gasoline = retriveValue(gasolineEditText)
            val ethanol = retriveValue(ethanolEditText)

            val result = ethanol / gasoline

            if(result < 0.7){
                mTextView.text = getString(R.string.answer_ethanol)
                mTextView.setTextColor(resources.getColor(R.color.ethanol, this.theme))
            }else{
                mTextView.text = getString(R.string.answer_gasoline)
                mTextView.setTextColor(resources.getColor(R.color.gasoline, this.theme))
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