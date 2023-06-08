package com.example.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calc.ui.theme.CalcTheme

class MainActivity<T> : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var input = remember {
                mutableStateOf("")
            }
            var accumulate = remember {
                mutableStateOf("")
            }
            var currentOperator = remember {
                mutableStateOf("")
            }
            fun cutPart(value: String):String{
                var isHavePointZeroPart = """-?\d+\.0""".toRegex().matches(value)
                if(isHavePointZeroPart){
                    return value.substringBefore('.')
                } else {
                    return  value
                }
            }
            fun calculate(operator: String){
                fun applayOperator(): String{
                    var result = 0.0
                    when(currentOperator.value){
                        "X" -> result = accumulate.value.toDouble() * input.value.toDouble()
                        "-" -> result = accumulate.value.toDouble() - input.value.toDouble()
                        "+" -> result = accumulate.value.toDouble() + input.value.toDouble()
                        "/" -> result = accumulate.value.toDouble() / input.value.toDouble()
                        "-" -> result = accumulate.value.toDouble() - input.value.toDouble()


                    }
                    var resultStr = cutPart(result.toString())
                    if(resultStr.length > 7){
                        resultStr = String.format("%.5f",resultStr.toDouble())
                    }
                    return resultStr
                }

                if(input.value == "ошибка"){
                    return
                }
                if(operator == "="){
                    if(currentOperator.value != ""){
                        if(input.value != "" && accumulate.value != ""){
                            var result = applayOperator().toString()
                            if(result == "Infinity" || result == "NaN"){
                                input.value = "ошибка"
                            } else {
                                input.value = result
                            }
                                accumulate.value = "";
                                currentOperator.value = ""
                        }
                    }
                } else
                if(accumulate.value == ""){
                    accumulate.value = input.value
                    input.value = ""
                    currentOperator.value = operator
                }
                else {
                    if(input.value == ""){
                        currentOperator.value = operator
                    } else
                    if(input.value != ""){
                        if(currentOperator.value != "" && currentOperator.value != operator){
                            var result = applayOperator()
                            if(result == "Infinity" || result == "NaN"){
                                accumulate.value = ""
                                input.value = "ошибка"
                                currentOperator.value = ""
                            } else {
                                accumulate.value = result
                                input.value = ""
                                currentOperator.value = operator
                            }

                        } else {
                            var result = ""
                            currentOperator.value = operator
                            result = applayOperator()
                            if(result == "Infinity" || result == "NaN"){
                                accumulate.value = ""
                                input.value = "ошибка"
                                currentOperator.value = ""
                            } else {
                                accumulate.value = result
                                input.value = ""
                            }
                        }
                    }
                }


//                if(currentOperator.value == ""){
//                    accumulate.value = input.value
//                    input.value = ""
//                    currentOperator.value = operator
//                } else {
//                    if(input.value != ""){
//                        input.value = applayOperator()
//                    }
//                }

            }


            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                Arrangement.Center,
                Alignment.CenterHorizontally

            ) {


                Row {
                    Text(
                        text = accumulate.value,
                        fontSize = 23.sp
                    )

                }
                Row {
//                    Text(
//                        text = "operator: " + currentOperator.value,
//                        fontSize = 23.sp
//                    )
                }
                Row {
                    Text(
                        text = input.value,
                        fontSize = 23.sp
                    )
                }
                Row {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                            input.value += "7"
                        }) {
                        Text("7", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "8"

                    }) {
                        Text("8", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "9"

                    }) {
                        Text("9", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        calculate("X")
                    }) {
                        Text("X", fontSize =23.sp)
                    }
                }
                Row {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "4"

                    }) {
                        Text("4", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "5"

                    }) {
                        Text("5", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "6"

                    }) {
                        Text("6", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        calculate("-")
                    }) {
                        Text("-", fontSize =23.sp)
                    }
                }
                Row {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "1"

                    }) {
                        Text("1", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "2"

                    }) {
                        Text("2", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                            if(input.value == "ошибка"){
                                input.value = ""
                            }
                        input.value += "3"

                    }) {
                        Text("3", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        calculate("+")
                    }) {
                        Text("+", fontSize =23.sp)
                    }
                }
                Row {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        if("""\d+\.""".toRegex().find(input.value) == null && input.value != "" && input.value != "ошибка"){
                            input.value += "."
                        }
                    }) {
                        Text(".", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        if(input.value != "0"){
                            if(input.value == "ошибка" || input.value == "0"){
                                input.value = ""
                            }
                            input.value += "0"
                        }
                    }) {
                        Text("0", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        calculate("/")
                    }) {
                        Text("/", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        calculate("=")
                    }) {
                        Text("=", fontSize =23.sp)
                    }
                }
                Row {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        accumulate.value = ""
                        input.value = ""
                        currentOperator.value = ""

                    }) {
                        Text("AC", fontSize =23.sp)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black,Color.White,Color.White),
                        border = BorderStroke(1.dp,color = Color.Black),
                        onClick = {
                        if(input.value != "" && input.value != "ошибка"){
                            input.value = cutPart((input.value.toDouble() * -1).toString())
                        }
                    }) {
                        Text("+/-", fontSize =23.sp)
                    }
                }
            }
        }
    }
}