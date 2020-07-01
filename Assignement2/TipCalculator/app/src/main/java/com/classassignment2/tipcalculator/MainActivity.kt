package com.classassignment2.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

const val HUNDRED_PERCENT = 100.00

class MainActivity : AppCompatActivity(){


    lateinit var  slider: SeekBar
    lateinit var  outcome: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.peopleAmountSpinner)
        val peopleAmount = resources.getStringArray(R.array.people)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, peopleAmount )

        var peopleSelected : String = ""



        slider = findViewById(R.id.seekBarTip) as SeekBar
        outcome = findViewById(R.id.textViewTip) as TextView




        fun calculateExpense() {

            val fullBill : Double = if(costOfBillEditText.text.isNotEmpty()) costOfBillEditText.text.toString().toDouble()else 1.00
            val totalPercent = if(outcome.text.isNotEmpty()) outcome.text.toString().toInt() else 1
            val totalPerPerson = if(peopleSelected.toString().isNotEmpty()) peopleSelected.toString().toInt() else 5

            val totalTip : Double =  fullBill * (totalPercent / HUNDRED_PERCENT)
            val totalExpense =  fullBill + totalTip
            val finalPerPerson = totalExpense / totalPerPerson

            outcomeTip.text = String.format("$%.2f", totalTip)
            outcomePay.text = String.format("$%.2f", totalExpense)
            outcomePerPerson.text = String.format("$%.2f",finalPerPerson )
        }


        costOfBillEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateExpense()
            }

        })

        slider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                outcome.text = (progress + 1).toString()
                calculateExpense()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                peopleSelected = peopleAmount[position]
                calculateExpense()

            }

        }

        spinner.adapter = adapter

    }





}