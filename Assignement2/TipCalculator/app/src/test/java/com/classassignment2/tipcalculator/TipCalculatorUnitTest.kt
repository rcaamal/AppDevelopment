package com.classassignment2.tipcalculator


import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorUnitTest {
    @Test
    fun calculateTip_ShouldEqualXWithFivePeople() {

        //Arrange
        val tipCalculators = TipCalculator();
        //Act
        val perPersonTotal: Double = tipCalculators.calculatePerPerson(totalBill = 100.00, numberOfPeople = 5, tipPercent = 20 )
        //Assert
        assertEquals(24.00, perPersonTotal,0.01)

    }

    @Test
    fun calculateTip_ShouldEqualXWithTwoPeople() {

        //Arrange
        val tipCalculators = TipCalculator();
        //Act
        val perPersonTotal: Double = tipCalculators.calculatePerPerson(totalBill = 20.00, numberOfPeople = 2, tipPercent = 20 )
        //Assert
        assertEquals(12.0, perPersonTotal,0.01)

    }
    @Test
    fun calculateTip_ShouldEqualXWithFourPeople() {

        //Arrange
        val tipCalculators = TipCalculator();
        //Act
        val perPersonTotal: Double = tipCalculators.calculatePerPerson(totalBill = 80.00, numberOfPeople = 4, tipPercent = 20 )
        //Assert
        assertEquals(24.00, perPersonTotal,0.01)

    }

    @Test
    fun calculateTip_ShouldEqualXWithEightPeople() {

        //Arrange
        val tipCalculators = TipCalculator();
        //Act
        val perPersonTotal: Double = tipCalculators.calculatePerPerson(totalBill = 160.00, numberOfPeople = 8, tipPercent = 20 )
        //Assert
        assertEquals(24.00, perPersonTotal,0.01)

    }

    @Test
    fun calculateTip_ShouldEqualXWithTenPeople() {

        //Arrange
        val tipCalculators = TipCalculator();
        //Act
        val perPersonTotal: Double = tipCalculators.calculatePerPerson(totalBill = 100.00, numberOfPeople = 10, tipPercent = 10 )
        //Assert
        assertEquals(11.00, perPersonTotal,0.01)

    }
}