package com.example.seventeen

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seventeen.ui.theme.SeventeenTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SeventeenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold {
                        val mutableList = remember{ mutableStateOf(listOf<MyObject>())}
                        Column(modifier = Modifier.fillMaxSize()) {
                            DrawMultiplyDragObject(mutableList.value)
                            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)) {

                                Button(onClick = {mutableList.value = mutableList.value + listOf(MyObject(size = 30.dp, Circle()))}) {
                                    Text(text = "Circle", fontSize = 20.sp)
                                }
                                FloatingActionButton(onClick = {mutableList.value=listOf()}, shape = CircleShape) {
                                    Icon(Icons.Rounded.Delete,"Delete All?")
                                }
                                Button(onClick = {mutableList.value = mutableList.value + listOf(MyObject(size = 30.dp, Rectangle()))}) {
                                    Text(text = "Rectangle", fontSize = 20.sp)
                                }

                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun DraggableObject(shape : Shape, size : Dp,color : Color = Color.Green) {
    val offsetX = remember{ mutableStateOf(0f)}
    val offsetY = remember{ mutableStateOf(0f)}

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = offsetX.value.roundToInt(),
                    y = offsetY.value.roundToInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
            .size(size)
            .clip(shape = shape)
            .background(color = color)
    )

}

@Composable
fun DrawMultiplyDragObject(listOfObjects : List<MyObject>){
    Column(modifier = Modifier.fillMaxWidth()){
        listOfObjects.map {DraggableObject(it.type.shape,it.size,color = it.color) }
    }
}