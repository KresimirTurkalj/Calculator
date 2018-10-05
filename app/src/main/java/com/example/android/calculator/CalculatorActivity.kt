package com.example.android.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*
import java.lang.Math.pow

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        button10.setOnClickListener {
            edit_text.append("(")
        }
        button20.setOnClickListener {
            edit_text.append(")")
        }
        button30.setOnClickListener {
            edit_text.append("^")
        }
        button40.setOnClickListener {
            edit_text.append("+")
        }
        button11.setOnClickListener {
            edit_text.append("1")
        }
        button21.setOnClickListener {
            edit_text.append("2")
        }
        button31.setOnClickListener {
            edit_text.append("3")
        }
        button41.setOnClickListener {
            edit_text.append("-")
        }
        button12.setOnClickListener {
            edit_text.append("4")
        }
        button22.setOnClickListener {
            edit_text.append("5")
        }
        button32.setOnClickListener {
            edit_text.append("6")
        }
        button42.setOnClickListener {
            edit_text.append("*")
        }
        button13.setOnClickListener {
            edit_text.append("7")
        }
        button23.setOnClickListener {
            edit_text.append("8")
        }
        button33.setOnClickListener {
            edit_text.append("9")
        }
        button43.setOnClickListener {
            edit_text.append("/")
        }
        button14.setOnClickListener {
            edit_text.append(".")
        }
        button24.setOnClickListener {
            edit_text.append("0")
        }
        button34.setOnClickListener {
            edit_text.append("*10^")
        }
        button44.setOnClickListener {
            edit_text.setText(calculateAnswer().toString())
        }
    }

    fun EditText.deleteLastChar() {
        val s: String = this.toString()
        this.setText(s.substring(0, s.length - 1))
    }

    private fun calculateAnswer(): Double {
        val s: String = edit_text.text.toString()
        return solutionRecurs(s)
    }

    private fun solutionRecurs(s: String): Double {
        for (i in 0 until s.length) {
            if (s[i] == ('+')) return solutionRecurs(s.substring(0, i)) + solutionRecurs(s.substring(i + 1, s.length))
            if (s[i] == ('-')) return solutionRecurs(s.substring(0, i)) - solutionRecurs(s.substring(i + 1, s.length))
        }
        for (i in 0 until s.length) {
            if (s[i] == ('*')) return solutionRecurs(s.substring(0, i)) * solutionRecurs(s.substring(i + 1, s.length))
            if (s[i] == ('/')) return solutionRecurs(s.substring(0, i)) / solutionRecurs(s.substring(i + 1, s.length))
        }
        for (i in 0 until s.length)
            if (s[i] == ('^')) return pow(solutionRecurs(s.substring(0, i)), solutionRecurs(s.substring(i + 1, s.length)))
        var i = 0
        var parentheses = false
        while (i < s.length) {
            if (s[i] == ('(')) {
                i = skip(s, i, s.length)
                parentheses = true
            }
            i++
        }
        if (parentheses) {
            return solutionRecurs(s)
        }
        try {
            return s.toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, R.string.ErrorString, Toast.LENGTH_LONG).show()
        }
        return 0.0
    }

    private fun skip(s: String, downEnd: Int, upEnd: Int): Int {
        var i = downEnd
        var numberOfPar = 0
        do {
            if (s[i] == '(') numberOfPar++
            if (s[i] == ')') numberOfPar--
            i++
        } while (i < upEnd && numberOfPar > 0)
        return i - 1
    }
}