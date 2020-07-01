package com.classassignment2.tipcalculator

const val  ONE_HUNDRED_PERCENT = 100.00

class TipCalculator {

    fun calculatePerPerson(totalBill : Double, numberOfPeople : Int, tipPercent : Int) : Double {

        val totalTip = totalBill * (tipPercent / HUNDRED_PERCENT)
        val totalExpense =  totalBill + totalTip
        val finalPerPerson = totalExpense / numberOfPeople

        return  finalPerPerson
    }


}