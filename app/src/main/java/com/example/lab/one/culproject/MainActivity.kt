package com.example.lab.one.culproject

/**
 * MainActivity.kt
 * Aviraj gill
 * 200458156
 * 2022-09-30
 * This is a calculator app, and is does the basic stuff nothing to too cool
 * This now still does basic stuff
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    //Function to add zero and . into screen res
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
            //If i have left side do right
            rhs = ResultLabel?.text.toString()
            haveRHS = true
            inputReady = false
        }
        else
        {
            //Else do left then right
            lhs = ResultLabel?.text.toString()
            haveLHS = true
            inputReady = false
        }

        if(haveLHS && haveRHS)
        {//If both then do operation
           Evaluate()
            return;// Stop function here so it does not go below and call Evaluate again. This breaks the app if not stopped
        }
        when(buttonText) //setting the operation value
        {
            "+" -> operation = "+"
            "-" -> operation = "-"
            "X" -> operation = "X"
            "/" -> operation = "/"
            "%" -> operation = "%"
            "=" -> Evaluate()
        }

    }

    //All extra buttons here
    fun ExtraButtons(view: View) {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text
        if(buttonText == "+/-")//This button was moved to extra so i
        // could use it and print the Left side res
        {
            lhs = plusmin(lhs).toString();
            return;
        }
        //C = clear
        if (buttonText == "C") {
            ResultLabel?.text = "0"
            Reset()
        } else {
            if (ResultLabel?.text?.count() == 1) {
                ResultLabel?.text = "0"
            } else {
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

    //All Op functions
    //Adding
    fun Addition(lhs: Float, rhs: Float): Float
    {
        return lhs + rhs
    }
    //Subing
    fun Subtraction(lhs: Float, rhs: Float): Float
    {
        return lhs - rhs
    }
    //Multipling
    fun Multiplication(lhs: Float, rhs: Float): Float
    {
        return lhs * rhs
    }
    //Dividing
    fun Division (lhs: Float, rhs: Float): Float
    {
        return lhs / rhs
    }
    //Percentage
    fun Percentage (lhs: Float, rhs: Float): Float
    {
        return (lhs / rhs) * 100
    }
    //plug and min not sure how to return without crashing
    fun plusmin (lhs: String): Float
    {
        return 1.4f;//Broken left 1.4f to stop my mis click crashes
        //((lhs.toFloat() - lhs.toFloat()) - lhs.toFloat())
    }
    fun Evaluate()
    {
        //A functions called here like an engine
        when(operation)
        {
            "+" -> result = Addition(lhs.toFloat(), rhs.toFloat())
            "-" -> result = Subtraction(lhs.toFloat(), rhs.toFloat())
            "X" -> result = Multiplication(lhs.toFloat(), rhs.toFloat())
            "/" -> result = Division(lhs.toFloat(), rhs.toFloat())
            "%" -> result = Percentage(lhs.toFloat(), rhs.toFloat())

        }

        //Reset for next call
        ResultLabel?.text  = result.toString()
        lhs = result.toString()
        rhs = ""
        haveRHS = false
    }

}