package com.example.lecture4_homework2_numbersapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            val input = editText.text.toString()
            editText.setText("")
            if (containsOnlyDigits(input) && isInRange(input)) {
                textView.setTextColor(Color.BLACK)
                textView.text = numToWord(input)
            } else {
                textView.setTextColor(Color.RED)
                textView.text = "შეიყვანეთ მხოლოდ რიცხვები 1-დან 1000-ის ჩათვლით"
            }
        }
    }

    class Numbers {
        val numbersMap = mapOf(
            1 to "ერთი",
            2 to "ორი",
            3 to "სამი",
            4 to "ოთხი",
            5 to "ხუთი",
            6 to "ექვსი",
            7 to "შვიდი",
            8 to "რვა",
            9 to "ცხრა",
            10 to "ათი",
            11 to "თერთმეტი",
            12 to "თორმეტი",
            13 to "ცამეტი",
            14 to "თოთხმეტი",
            15 to "თხუთმეტი",
            16 to "თექვსმეტი",
            17 to "ჩვიდმეტი",
            18 to "თვრამეტი",
            19 to "ცხრამეტი",
            20 to "ოცი",
            100 to "ასი"
        )
    }

    private fun containsOnlyDigits(input: String): Boolean {
        return Regex("^[0-9]+$").matches(input)
    }

    private fun isInRange(input: String): Boolean {
        return input.length < 4 || input == "1000"
    }

    private fun numToWord(input: String): String {
        val numbers = Numbers()
        val inputNum = input.toInt()
        var output = ""

        fun numsUntilHundred(inputNum: Int): String {
            var tensOutput = ""

            if (inputNum in numbers.numbersMap) {
                return numbers.numbersMap[inputNum]!!
            }

            if (inputNum in 20..39) {
                tensOutput = numbers.numbersMap[20]!!
            } else {
                val multipleOfTwenty = inputNum / 20
                val multipleOfTwentyStr = numbers.numbersMap[multipleOfTwenty]
                tensOutput += multipleOfTwentyStr!!.substring(0, multipleOfTwentyStr.length - 1)
                if (multipleOfTwenty in listOf(2, 4)) {
                    tensOutput += "მ"
                }
                tensOutput += numbers.numbersMap[20]
            }

            if (inputNum !in 20..80 step 20) {
                tensOutput =
                    tensOutput.substring(0, tensOutput.length - 1) + "და" + numbers.numbersMap[inputNum - inputNum / 20 * 20]
            }

            return tensOutput
        }

        fun numsAfterHundred(inputNum: Int): String {
            var hundredsOutput = ""
            val hundred = numbers.numbersMap[100]!!

            if (inputNum == 100) {
                return hundred
            } else {
                val multipleOfHundred = inputNum / 100
                val multipleOfHundredStr = numbers.numbersMap[multipleOfHundred]!!
                val hundredAnd = hundred.substring(0, hundred.length - 1)
                hundredsOutput = when(multipleOfHundred) {
                    1 -> hundredAnd
                    8, 9 -> multipleOfHundredStr + hundredAnd
                    else -> multipleOfHundredStr.substring(0, multipleOfHundredStr.length - 1) + hundredAnd
                }
                hundredsOutput += when (inputNum % 100) {
                    0 -> "ი"
                    else -> " " + numsUntilHundred(inputNum - multipleOfHundred * 100)
                }
            }

            return hundredsOutput
        }

        when {
            input.length <= 2 -> output = numsUntilHundred(inputNum)
            input.length <= 4 -> output = numsAfterHundred(inputNum)
        }
        return output
    }
}