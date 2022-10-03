package com.example.lab.one.culproject
/**
 * MainActivity.kt
 * Aviraj gill
 * 200458156
 * 2022-09-30
 * This is a calculator app, and is does the basic stuff nothing to too cool
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    // Instance members of the MainActivity class
    var ResultLabel: TextView? = null
    var result: Float = 0.0f
    var lhs: String = ""
    var rhs: String = ""
    var haveLHS: Boolean = false
    var haveRHS: Boolean = false
    var operation: String = ""
    var inputReady: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ResultLabel = findViewById<TextView>(R.id.Result_TextView)
    }

    fun NumberButtons(view: View)
    {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text
        if((ResultLabel?.text == "0") || !inputReady)
        {
            ResultLabel?.text = buttonText
            inputReady = true
        }
        else
        {
            if (buttonText == ".")
            {
                if(ResultLabel?.text?.contains(".") != true)
                {
                    ResultLabel?.text = ResultLabel?.text.toString().plus(buttonText)
                }
            }
            else
            {
                ResultLabel?.text = ResultLabel?.text.toString().plus(buttonText)
            }
        }
    }

    fun OperatorButtons(view: View)
    {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text

        if(haveLHS)
        {
            rhs = ResultLabel?.text.toString()
            haveRHS = true
            inputReady = false
        }
        else
        {
            lhs = ResultLabel?.text.toString()
            haveLHS = true
            inputReady = false
        }

        if(haveLHS && haveRHS)
        {
            Evaluate()
        }

        when(buttonText)
        {
            "+" -> operation = "+"
            "-" -> operation = "-"
            "X" -> operation = "X"
            "/" -> operation = "/"
            "=" -> Evaluate()
        }

    }

    fun ExtraButtons(view: View)
    {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text
        if(buttonText == "C")
        {
            ResultLabel?.text = "0"
            Reset()
        }
        else
        {
            if(ResultLabel?.text?.count() == 1)
            {
                ResultLabel?.text = "0"
            }
            else
            {
                ResultLabel?.text = ResultLabel?.text.toString().dropLast(1)
            }
        }

    }

    // Calculator Evaluation Functions
    fun Reset()
    {
        result = 0.0f
        lhs = ""
        rhs = ""
        haveLHS = false
        haveRHS = false
        operation = ""
        var inputReady: Boolean = true
    }

    fun Addition(lhs: Float, rhs: Float): Float
    {
        return lhs + rhs
    }

    fun Subtraction(lhs: Float, rhs: Float): Float
    {
        return lhs - rhs
    }

    fun Multiplication(lhs: Float, rhs: Float): Float
    {
        return lhs * rhs
    }

    fun Division (lhs: Float, rhs: Float): Float
    {
        return lhs / rhs
    }

    fun Evaluate()
    {
        when(operation)
        {
            "+" -> result = Addition(lhs.toFloat(), rhs.toFloat())
        }

        ResultLabel?.text  = result.toString()
        lhs = result.toString()
        rhs = ""
        haveRHS = false
    }

}
