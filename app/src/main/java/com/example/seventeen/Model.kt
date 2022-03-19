package com.example.seventeen

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import kotlin.random.Random

data class MyObject(val size : Dp, val type : ObjectType, val color : Color = colorList[Random.nextInt(colorList.size-1)]){
    companion object{
        val colorList = listOf(Color.Black,Color.Green,Color.Blue,Color.Red)
    }
}

sealed class ObjectType{
    abstract val shape: Shape
}

class Circle(override val shape: Shape = CircleShape) : ObjectType(){
}
class Rectangle(override val shape: Shape = RectangleShape) : ObjectType(){
}
