package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

abstract class MainActivity : AppCompatActivity() {
    private val button0: Button = findViewById(R.id.btn0)
    private val button1: Button = findViewById(R.id.btn1)
    private val button2: Button = findViewById(R.id.btn2)
    private val button3: Button = findViewById(R.id.btn3)
    private val button4: Button = findViewById(R.id.btn4)
    private val button5: Button = findViewById(R.id.btn5)
    private val button6: Button = findViewById(R.id.btn6)
    private val button7: Button = findViewById(R.id.btn7)
    private val button8: Button = findViewById(R.id.btn8)
    private val button9: Button = findViewById(R.id.btn9)

    private val buttonPercentage: Button = findViewById(R.id.btnPercentage)
    private val buttonRoot: Button = findViewById(R.id.btnSquareRoot)
    private val buttonSquare: Button = findViewById(R.id.btnSquare)
    private val buttonFraction: Button = findViewById(R.id.btnFraction)
    private val buttonCE: Button = findViewById(R.id.btnCE)
    private val buttonC: Button = findViewById(R.id.btnC)
    private val buttonBackspace: Button = findViewById(R.id.btnBackspace)
    private val buttonDivide: Button = findViewById(R.id.btnDivide)
    private val buttonMultiply: Button = findViewById(R.id.btnMultiple)
    private val buttonSubtract: Button = findViewById(R.id.btnSubtract)
    private val buttonAdd: Button = findViewById(R.id.btnAdd)
    private val buttonEqual: Button = findViewById(R.id.btnEqual)
    private val buttonPlusMinus: Button = findViewById(R.id.btnPlusMinus)
    private val buttonComma: Button = findViewById(R.id.btnComma)

    private val textViewHistoryText: Button = findViewById(R.id.txtNumberHistory)
    private val textViewCurrentNumber: Button = findViewById(R.id.txtNumberCurrent)

    private var isFutureOperationButtonClicked: Boolean = false
    private var isInstantOperationButtonClicked: Boolean = false
    private var isEqualButtonClicked: Boolean = false

    private var currentNumber: Double = 0.0 // Value can be changed.
    private var currentResult: Double = 0.0
    private var memory: Double = 0.0

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

    private val INIT = ""

    private val ADD = " + "
    private val SUBTRACT = " − "
    private val MULTIPLy = " × "
    private val DIVIDE = " ÷ "

    private val PERCENTAGE = ""
    private val SQUARE_ROOT = "√"
    private val SQUARE = "sqr"
    private val FRACTION = "1/"

    private val NEGATE = "negate"
    private val COMMA = ","
    private val EQUAL = " = "

    private var currentOperation = INIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}