package com.example.sampleproject.kotlinbasics

fun main() {
    //var d = Car()
    val car = Vehicle.Car
    car.display()

    val bike = Vehicle.Bike
    bike.display()

    val obj = Fruit.Apple()
    val obj1 = Fruit.Mango()
    val obj2 = Fruit.SmallMango()
    val obj3= Fruit.Orange()

    display(obj)
    display(obj1)
    display(obj2)
    display(obj3)
    display(null)
}

sealed class Vehicle {

    object Car : Vehicle() {
        fun display() {
            println("Subclass Car of sealed class Vehicle")
        }
    }

    object Bike : Vehicle() {
        fun display() {
            println("Subclass Bike of sealed class Vehicle")
        }
    }
}

sealed class Fruit(val x: String) {
    class Apple : Fruit("Apple")
    class Mango : Fruit("Mango")
    class SmallMango : Fruit("SmallMango")
    class Orange : Fruit("Orange")
}


fun display(fruit: Fruit?) {
    when (fruit) {
        is Fruit.Apple -> println("${fruit.x} is Apple")
        is Fruit.Mango,  is Fruit.SmallMango  -> println("${fruit.x} is Mango")
        is Fruit.Orange  -> println("${fruit.x} is Orange")
        else  -> println("${fruit?.x} print else")
    }
}