package assignment

import mu.KotlinLogging
import models.Character

private val logger = KotlinLogging.logger {}
var character = Character()

fun main(args: Array<String>){
    logger.info { "Launching Evan's Character Manager Console App" }

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addCharacter()
            2 -> println("You Chose Update Character")
            3 -> println("You Chose List All Characters")
            4 -> println("You Delete a Character")
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Evan's Character Manager Console App" }

}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Character")
    println(" 2. Update Character")
    println(" 3. List All Characters")
    println(" 4. Delete a Character")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addCharacter(){
    println("Add Character\n")
    print("Enter a Name : ")
    character.name = readLine()!!
    print("Enter a Race : ")
    character.race = readLine()!!
    print("Enter a Class : ")
    character.clss = readLine()!!
    println("You entered [ " + character.name + " ] for name " +
            "and [ " + character.race + " ] for race and [ "+ character.clss + " ] for class")
}