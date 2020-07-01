package com.classwork.cakeproject.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.classwork.cakeproject.R
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class OrderPageActivity  : AppCompatActivity(){

    lateinit var editTextFName: EditText
    lateinit var editTextLName: EditText
    lateinit var spinnerCakeType: Spinner
    lateinit var spinnerCakeNumber: Spinner
    lateinit var spinnerCakeSize: Spinner
    lateinit var spinnerCakeDesign: Spinner
    lateinit var editTextDescription: EditText
    lateinit var spinnerDeliver: Spinner
    lateinit var editTextAddress: EditText
    lateinit var buttonSubmitOrder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_page)

        editTextFName = findViewById(R.id.textFirstName)
        editTextLName = findViewById(R.id.textLastName)
        spinnerCakeType = findViewById(R.id.spinnerCakeType)
        spinnerCakeNumber = findViewById(R.id.spinnerNumberofCakes)
        spinnerCakeSize = findViewById(R.id.spinnerCakeSize)
        spinnerCakeDesign = findViewById(R.id.spinnerChooseDesign)
        editTextDescription = findViewById(R.id.textCakeDesign)
        spinnerDeliver = findViewById(R.id.spinnerCakeDelivery)
        editTextAddress = findViewById(R.id.textPostlAddress)
        buttonSubmitOrder = findViewById(R.id.buttonOrder)

        buttonSubmitOrder.setOnClickListener {

            saveOrder()

        }

        val spinnerType = findViewById<Spinner>(R.id.spinnerCakeType)
        val spinnerNumber = findViewById<Spinner>(R.id.spinnerNumberofCakes)
        val spinnerSize = findViewById<Spinner>(R.id.spinnerCakeSize)
        val spinnerDesign = findViewById<Spinner>(R.id.spinnerChooseDesign)
        val spinnerDelivery = findViewById<Spinner>(R.id.spinnerCakeDelivery)

        val cakeTypeArray  = resources.getStringArray(R.array.cakeType)
        val cakeNumberArray = resources.getStringArray(R.array.numberofCakes)
        val cakeSizeArray = resources.getStringArray(R.array.cakeSize)
        val cakeDesignArray = resources.getStringArray(R.array.chooseDesign)
        val cakeDeliveryArray = resources.getStringArray(R.array.cakeDelivery)

        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cakeTypeArray)
        var typeSelected : String = ""

        val numberAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cakeNumberArray)
        var numberSelected : String = ""

        val sizeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cakeSizeArray)
        var sizeSelected : String = ""

        val designAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cakeDesignArray)
        var designSelected : String = ""

        val deliveryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cakeDeliveryArray)
        var deliverySelected: String = ""

        val userId = FirebaseAuth.getInstance().currentUser!!.uid


        spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                typeSelected = cakeTypeArray[position]
            }

        }

        spinnerNumber.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                numberSelected = cakeNumberArray[position]
            }

        }

        spinnerSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sizeSelected = cakeSizeArray[position]
            }
        }

        spinnerDesign.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                designSelected = cakeDeliveryArray[position]
            }

        }

        spinnerDelivery.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                deliverySelected = cakeDeliveryArray[position]
            }

        }

        spinnerType.adapter = typeAdapter
        spinnerNumber.adapter = numberAdapter
        spinnerSize.adapter = sizeAdapter
        spinnerDesign.adapter = designAdapter
        spinnerDelivery.adapter = deliveryAdapter

        }
            private fun saveOrder(){
                val fname = editTextFName.text.toString().trim()
                val lname = editTextLName.text.toString().trim()
                val cType = spinnerCakeType.selectedItem.toString().trim()
                val cNumber = spinnerCakeNumber.selectedItem.toString().trim()
                val cSize = spinnerCakeSize.selectedItem.toString().trim()
                val cDesign = spinnerCakeDesign.selectedItem.toString().trim()
                val descrip = editTextDescription.text.toString().trim()
                val address = editTextAddress.text.toString().trim()


                if(fname.isEmpty()){
                    editTextFName.error = "Please enter your first name"
                    return
                }
                if(lname.isEmpty()){
                    editTextLName.error = "Please enter your last name"
                    return
                }

                if(descrip.isEmpty()){
                    editTextDescription.error = "Please add a description of your cake"
                    return
                }

                if(address.isEmpty()){
                    editTextAddress.error= "Please enter your full address"
                    return
                }

                val ref = FirebaseDatabase.getInstance().reference

                val orderId = ref.child("Orders").push().key

                val order = OrderActivity(orderId.toString(),fname,lname,cType,cNumber,cSize,cDesign,descrip,address)

                ref.child(orderId.toString()).setValue(order)

            }

    }
