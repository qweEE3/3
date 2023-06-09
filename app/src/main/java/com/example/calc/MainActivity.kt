package com.example.calc
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.example.calc.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun cutPart(value: String): String {
            var isHavePointZeroPart = """-?\d+\.0""".toRegex().matches(value)
            if (isHavePointZeroPart) {
                return value.substringBefore('.')
            } else {
                return value
            }
        }

        fun calculate(operator: String) {
            fun applayOperator(): String {
                var result = 0.0
                when (binding.currentOperator.text) {
                    "X" -> result = binding.accumulate.text.toString()
                        .toDouble() * binding.input.text.toString().toDouble()

                    "-" -> result = binding.accumulate.text.toString()
                        .toDouble() - binding.input.text.toString().toDouble()

                    "+" -> result = binding.accumulate.text.toString()
                        .toDouble() + binding.input.text.toString().toDouble()

                    "/" -> result = binding.accumulate.text.toString()
                        .toDouble() / binding.input.text.toString().toDouble()

                    "-" -> result = binding.accumulate.text.toString()
                        .toDouble() - binding.input.text.toString().toDouble()


                }
                var resultStr = cutPart(result.toString())
                if (resultStr.length > 7) {
                    resultStr = String.format("%.5f", resultStr.toDouble())
                }
                return resultStr
            }

            if (binding.input.text == "ошибка") {
                return
            }
            if (operator == "=") {
                if (binding.currentOperator.text != "") {
                    if (binding.input.text != "" && binding.accumulate.text != "") {
                        var result = applayOperator().toString()
                        if (result == "Infinity" || result == "NaN") {
                            binding.input.text = "ошибка"
                        } else {
                            binding.input.text = result
                        }
                        binding.accumulate.text = "";
                        binding.currentOperator.text = ""
                    }
                }
            } else
                if (binding.accumulate.text == "") {
                    binding.accumulate.text = binding.input.text
                    binding.input.text = ""
                    binding.currentOperator.text = operator
                } else {
                    if (binding.input.text == "") {
                        binding.currentOperator.text = operator
                    } else
                        if (binding.input.text != "") {
                            if (binding.currentOperator.text != "" && binding.currentOperator.text != operator) {
                                var result = applayOperator()
                                if (result == "Infinity" || result == "NaN") {
                                    binding.accumulate.text = ""
                                    binding.input.text = "ошибка"
                                    binding.currentOperator.text = ""
                                } else {
                                    binding.accumulate.text = result
                                    binding.input.text = ""
                                    binding.currentOperator.text = operator
                                }

                            } else {
                                var result = ""
                                binding.currentOperator.text = operator
                                result = applayOperator()
                                if (result == "Infinity" || result == "NaN") {
                                    binding.accumulate.text = ""
                                    binding.input.text = "ошибка"
                                    binding.currentOperator.text = ""
                                } else {
                                    binding.accumulate.text = result
                                    binding.input.text = ""
                                }
                            }
                        }
                }
        }

        binding.button0.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "0"
        }
        binding.button1.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "1"
        }
        binding.button2.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "2"
        }
        binding.button3.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "3"
        }
        binding.button4.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "4"
        }
        binding.button5.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "5"
        }
        binding.button6.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "6"
        }
        binding.button7.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "7"
        }
        binding.button8.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "8"
        }
        binding.button9.setOnClickListener {
            if (binding.input.text == "ошибка" || binding.input.text == "0") {
                binding.input.text = ""
            }
            binding.input.text = binding.input.text.toString() + "9"
        }


        binding.ac.setOnClickListener {
            binding.accumulate.text = ""
            binding.input.text = ""
            binding.currentOperator.text = ""
        }
        binding.plus.setOnClickListener {
            calculate("+")
        }
        binding.minus.setOnClickListener {
            calculate("-")
        }
        binding.multiply.setOnClickListener {
            calculate("X")
        }
        binding.devide.setOnClickListener {
            calculate("/")
        }
        binding.point.setOnClickListener {
            if ("""\d+\.""".toRegex()
                    .find(binding.input.text) == null && binding.input.text != "" && binding.input.text != "ошибка"
            ) {
                binding.input.text = binding.input.text.toString() + "."
            }
        }
        binding.plusMinus.setOnClickListener {
            if (binding.input.text != "" && binding.input.text != "ошибка") {
                binding.input.text =
                    cutPart((binding.input.text.toString().toDouble() * -1).toString())
            }
        }
        binding.result.setOnClickListener {
            calculate("=")
        }
        binding.percent.setOnClickListener {
            if (binding.input.text != "" && binding.input.text != "ошибка" && binding.input.text != "0") {
                var value = cutPart((binding.input.text.toString().toDouble() / 100).toString())
                binding.input.text = value
            }
        }
    }
}