@file:Suppress("PrivatePropertyName")

package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button

    private lateinit var buttonPercentage: Button
    private lateinit var buttonSquareRoot: Button
    private lateinit var buttonSquare: Button
    private lateinit var buttonFraction: Button
    private lateinit var buttonCE: Button
    private lateinit var buttonC: Button
    private lateinit var buttonBackspace: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonAdd: Button
    private lateinit var buttonEqual: Button
    private lateinit var buttonPlusMinus: Button
    private lateinit var buttonComma: Button

    private lateinit var textViewHistoryText: AppCompatTextView
    private lateinit var textViewCurrentNumber: AppCompatTextView

    private var isOperationButtonClicked: Boolean = false
    private var isInstantOperationButtonClicked: Boolean = false
    private var isEqualButtonClicked: Boolean = false

    private var currentNumber: Double = 0.0 // Value can be changed.
    private var currentResult: Double = 0.0

    private var historyText = ""
    private var historyInstantOperationText = ""

    private val ZERO: String = "0"
    private val ONE: String = "1"
    private val TWO: String = "2"
    private val THREE: String = "3"
    private val FOUR: String = "4"
    private val FIVE: String = "5"
    private val SIX: String = "6"
    private val SEVEN: String = "7"
    private val EIGHT: String = "8"
    private val NINE: String = "9"

    private val ADD = " + "
    private val SUBTRACT = " − "
    private val MULTIPLY = " × "
    private val DIVIDE = " ÷ "

    private val PERCENTAGE = ""
    private val SQUARE_ROOT = "√"
    private val SQUARE = "sqr"
    private val FRACTION = "1/"

    private val NEGATE = "-"
    private val COMMA = ","

    private val EMPTY_STRING = ""
    private var currentOperation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0 = findViewById(R.id.btn0)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        button5 = findViewById(R.id.btn5)
        button6 = findViewById(R.id.btn6)
        button7 = findViewById(R.id.btn7)
        button8 = findViewById(R.id.btn8)
        button9 = findViewById(R.id.btn9)

        buttonPercentage = findViewById(R.id.btnPercentage)
        buttonSquareRoot = findViewById(R.id.btnSquareRoot)
        buttonSquare = findViewById(R.id.btnSquare)
        buttonFraction = findViewById(R.id.btnFraction)
        buttonCE = findViewById(R.id.btnCE)
        buttonC = findViewById(R.id.btnC)
        buttonBackspace = findViewById(R.id.btnBackspace)
        buttonDivide = findViewById(R.id.btnDivide)
        buttonAdd = findViewById(R.id.btnAdd)
        buttonSubtract = findViewById(R.id.btnSubtract)
        buttonMultiply = findViewById(R.id.btnMultiply)
        buttonEqual = findViewById(R.id.btnEqual)
        buttonPlusMinus = findViewById(R.id.btnPlusMinus)
        buttonComma = findViewById(R.id.btnComma)

        textViewHistoryText = findViewById(R.id.txtNumberHistory)
        textViewCurrentNumber = findViewById(R.id.txtNumberCurrent)

        button0.setOnClickListener {
            onNumberButtonClick(ZERO)
        }

        button1.setOnClickListener {
            onNumberButtonClick(ONE)
        }

        button2.setOnClickListener {
            onNumberButtonClick(TWO)
        }

        button3.setOnClickListener {
            onNumberButtonClick(THREE)
        }

        button4.setOnClickListener {
            onNumberButtonClick(FOUR)
        }

        button5.setOnClickListener {
            onNumberButtonClick(FIVE)
        }

        button6.setOnClickListener {
            onNumberButtonClick(SIX)
        }

        button7.setOnClickListener {
            onNumberButtonClick(SEVEN)
        }

        button8.setOnClickListener {
            onNumberButtonClick(EIGHT)
        }

        button9.setOnClickListener {
            onNumberButtonClick(NINE)
        }

        buttonAdd.setOnClickListener {
            onOperationButtonClick(ADD)
        }

        buttonSubtract.setOnClickListener {
            onOperationButtonClick(SUBTRACT)
        }

        buttonMultiply.setOnClickListener {
            onOperationButtonClick(MULTIPLY)
        }

        buttonDivide.setOnClickListener {
            onOperationButtonClick(DIVIDE)
        }

        buttonPercentage.setOnClickListener {
            onInstantOperationButtonClick(PERCENTAGE)
        }

        buttonSquareRoot.setOnClickListener {
            onInstantOperationButtonClick(SQUARE_ROOT)
        }

        buttonSquare.setOnClickListener {
            onInstantOperationButtonClick(SQUARE)
        }

        buttonFraction.setOnClickListener {
            onInstantOperationButtonClick(FRACTION)
        }

        buttonCE.setOnClickListener {
            clearEntry()
        }

        buttonC.setOnClickListener {
            clear()
        }

        buttonBackspace.setOnClickListener {
            onBackspaceButtonClick()
        }

        buttonPlusMinus.setOnClickListener {
            onPlusMinusButtonClick()
        }

        buttonComma.setOnClickListener {
            onCommaButtonClick()
        }

        buttonEqual.setOnClickListener {
            onEqualButtonClick()
        }

    }

    private fun onNumberButtonClick(number: String) {

        var currentValue: String = textViewCurrentNumber.text.toString()
        currentValue =
            if (currentValue == ZERO || isOperationButtonClicked || isInstantOperationButtonClicked || isEqualButtonClicked) number
            else buildString(arrayOf(currentValue, number))

        try {
            currentNumber = formatStringToDouble(currentValue)
        } catch (e: ParseException) { }

        textViewCurrentNumber.text = currentValue

        if (isEqualButtonClicked) {
            currentOperation = EMPTY_STRING
            historyText = EMPTY_STRING
        }

        if (isInstantOperationButtonClicked) {
            historyInstantOperationText = EMPTY_STRING
            textViewHistoryText.text = buildString(arrayOf(historyText, currentOperation))
            isInstantOperationButtonClicked = false
        }

        setCondition(
            isOperationButtonClicked = false,
            isEqualButtonClicked = false,
            isInstantOperationButtonClicked = isInstantOperationButtonClicked
        )
    }

    private fun onOperationButtonClick(operation: String) {

        if (!isOperationButtonClicked && !isEqualButtonClicked) {
            calculateResult()
        }

        currentOperation = operation

        if (isInstantOperationButtonClicked) {
            isInstantOperationButtonClicked = false
            historyText = textViewHistoryText.text.toString()
        }
        textViewHistoryText.text = buildString(arrayOf(historyText, operation))

        setCondition(
            isOperationButtonClicked = true,
            isEqualButtonClicked = false,
            isInstantOperationButtonClicked = isInstantOperationButtonClicked
        )
    }

    private fun onInstantOperationButtonClick(operation: String) {

        var currentValue: String = textViewCurrentNumber.text.toString()
        var thisOperationNumber: Double = formatStringToDouble(currentValue)

        currentValue = "(${formatDoubleToString(thisOperationNumber)})"

        when (operation) {
            PERCENTAGE -> {
                thisOperationNumber = (currentResult * thisOperationNumber) / 100
                currentValue = formatDoubleToString(thisOperationNumber)
            }
            SQUARE_ROOT -> thisOperationNumber = sqrt(thisOperationNumber)
            SQUARE -> thisOperationNumber *= thisOperationNumber
            FRACTION -> thisOperationNumber = 1 / thisOperationNumber
        }

        when {
            isInstantOperationButtonClicked -> {
                historyInstantOperationText = "($historyInstantOperationText)"
                historyInstantOperationText = buildString(arrayOf(operation, historyInstantOperationText))
                textViewHistoryText.text =
                    if (isEqualButtonClicked) historyInstantOperationText
                    else buildString(arrayOf(historyText, currentOperation, historyInstantOperationText))
            }
            isEqualButtonClicked -> {
                historyInstantOperationText = buildString(arrayOf(operation, currentValue))
                textViewHistoryText.text = historyInstantOperationText
            }
            else -> {
                historyInstantOperationText = buildString(arrayOf(operation, currentValue))
                textViewHistoryText.text = buildString(arrayOf(historyText, currentOperation, historyInstantOperationText))
            }
        }

        textViewCurrentNumber.text = formatDoubleToString(thisOperationNumber)

        if (isEqualButtonClicked) currentResult = thisOperationNumber else currentNumber = thisOperationNumber

        isInstantOperationButtonClicked = true
        isOperationButtonClicked = false
        setCondition(false, isEqualButtonClicked, true)
    }

    private fun clearEntry(newNumber: Double = 0.0) {
        historyInstantOperationText = EMPTY_STRING

        if (isEqualButtonClicked) {
            currentOperation = EMPTY_STRING
            historyText = EMPTY_STRING
        }

        if (isInstantOperationButtonClicked)
            textViewHistoryText.text = buildString(arrayOf(historyText, currentOperation))

        setCondition(
            isOperationButtonClicked = false,
            isEqualButtonClicked = false,
            isInstantOperationButtonClicked = false
        )

        currentNumber = newNumber
        textViewCurrentNumber.text = formatDoubleToString(newNumber)
    }

    private fun clear() {
        currentNumber = 0.0
        currentResult = 0.0
        currentOperation = EMPTY_STRING

        historyText = EMPTY_STRING
        historyInstantOperationText = EMPTY_STRING

        textViewCurrentNumber.text = formatDoubleToString(currentNumber)
        textViewHistoryText.text = historyText

        setCondition(
            isOperationButtonClicked = false,
            isEqualButtonClicked = false,
            isInstantOperationButtonClicked = false
        )
    }

    private fun onBackspaceButtonClick() {
        if (isOperationButtonClicked || isInstantOperationButtonClicked || isEqualButtonClicked) return

        var currentValue: String = textViewCurrentNumber.text.toString()

        val charsLimit = if (currentValue.first().isDigit()) 1 else 2

        if (currentValue.length > charsLimit)
            currentValue = currentValue.substring(0, currentValue.length - 1)
        else
            currentValue = ZERO

        textViewCurrentNumber.text = currentValue
        currentNumber = formatStringToDouble(currentValue)
    }

    private fun onPlusMinusButtonClick() {
        val currentValue: String = textViewCurrentNumber.text.toString()

        currentNumber = formatStringToDouble(currentValue)
        if (currentNumber == 0.0) return

        currentNumber *= -1
        textViewCurrentNumber.text = formatDoubleToString(currentNumber)

        if (isInstantOperationButtonClicked) {
            historyInstantOperationText = "($historyInstantOperationText)"
            historyInstantOperationText = buildString(arrayOf(NEGATE, historyInstantOperationText))
            textViewHistoryText.text = buildString(arrayOf(historyText, currentOperation, historyInstantOperationText))
        }

        if (isEqualButtonClicked) {
            currentOperation = EMPTY_STRING
        }

        isOperationButtonClicked = false
        isEqualButtonClicked = false

        setCondition(
            isOperationButtonClicked = false,
            isEqualButtonClicked = false,
            isInstantOperationButtonClicked = isInstantOperationButtonClicked
        )
    }

    private fun onCommaButtonClick() {
        var currentValue: String = textViewCurrentNumber.text.toString()

        if (isOperationButtonClicked || isInstantOperationButtonClicked || isEqualButtonClicked) {
            currentValue = StringBuilder().append(ZERO).append(COMMA).toString()
            if (isInstantOperationButtonClicked) {
                historyInstantOperationText = EMPTY_STRING
                textViewHistoryText.text = buildString(arrayOf(historyText, currentOperation))
            }
            if (isEqualButtonClicked) currentOperation = EMPTY_STRING
            currentNumber = 0.0
        } else if (currentValue.contains(COMMA)) {
            return
        } else currentValue = StringBuilder().append(currentValue).append(COMMA).toString()

        textViewCurrentNumber.text = currentValue

        setCondition(
            isOperationButtonClicked = false,
            isEqualButtonClicked = false,
            isInstantOperationButtonClicked = false
        )
    }

    private fun onEqualButtonClick() {
        if (isOperationButtonClicked) {
            currentNumber = currentResult
        }

        if (!isEqualButtonClicked) calculateResult()

        historyText = buildString(arrayOf(formatDoubleToString(currentResult)))

        textViewHistoryText.text = EMPTY_STRING

        setCondition(
            isOperationButtonClicked = false,
            isEqualButtonClicked = true,
            isInstantOperationButtonClicked = isInstantOperationButtonClicked
        )
    }

    private fun calculateResult() {

        when (currentOperation) {
            EMPTY_STRING -> {
                currentResult = currentNumber
                historyText = buildString(arrayOf(textViewHistoryText.text.toString()))
            }
            ADD -> currentResult += currentNumber
            SUBTRACT -> currentResult -= currentNumber
            MULTIPLY -> currentResult *= currentNumber
            DIVIDE -> currentResult /= currentNumber
        }

        textViewCurrentNumber.text = formatDoubleToString(currentResult)

        if (isInstantOperationButtonClicked) {
            isInstantOperationButtonClicked = false
            historyText = textViewHistoryText.text.toString()
            if (isEqualButtonClicked)
                historyText = buildString(arrayOf(historyText, currentOperation, formatDoubleToString(currentNumber)))
        } else {
            historyText = buildString(arrayOf(historyText, currentOperation, formatDoubleToString(currentNumber)))
        }
    }

    private fun useNumberFormat(): DecimalFormat {

        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = ','

        val format = DecimalFormat("#.####")
        format.decimalFormatSymbols = symbols

        return format
    }

    private fun formatStringToDouble(number: String): Double {
        return useNumberFormat().parse(number).toDouble()
    }

    private fun formatDoubleToString(number: Double): String {
        return useNumberFormat().format(number)
    }

    private fun buildString(arr: Array<String>): String {
        val stringBuilder = StringBuilder()

        arr.forEach { stringBuilder.append(it) }

        return stringBuilder.toString()
    }

    private fun setCondition(isOperationButtonClicked: Boolean, isEqualButtonClicked: Boolean, isInstantOperationButtonClicked: Boolean) {
        this.isOperationButtonClicked = isOperationButtonClicked
        this.isInstantOperationButtonClicked = isInstantOperationButtonClicked
        this.isEqualButtonClicked = isEqualButtonClicked
    }
}