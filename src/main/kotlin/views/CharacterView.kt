package views
import models.CharacterJSONStore
import models.CharacterModel

class CharacterView {

    fun menu() : Int {

        val option : Int
        val input: String?

        println("MAIN MENU")
        println(" 1. Add Character")
        println(" 2. Update Character")
        println(" 3. List All Characters")
        println(" 4. Search Characters")
        println(" 5. Delete Character")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }


    fun listCharacters(characters : CharacterJSONStore) {
        println("List All Characters")
        println()
        characters.logAll()
        println()
    }


    fun showCharacter(character : CharacterModel) {
        if(character != null) {
            println("Character Details:")
            println("Name: " + character.name)
            println("Race: " + character.race)
            println("Class: " + character.clss)
            println("id: " + character.id)
        }
        else
            println("Character Not Found...")
    }

    fun addCharacterData(character : CharacterModel) : Boolean {

        println()
        print("Enter a name : ")
        character.name = readLine()!!
        print("Enter a race : ")
        character.race = readLine()!!
        print("Enter a class : ")
        character.clss = readLine()!!
        return character.name.isNotEmpty() && character.race.isNotEmpty() && character.clss.isNotEmpty()
    }

    fun updateCharacterData(character : CharacterModel) : Boolean {

        val tempName: String?
        val tempRace: String?
        val tempClss: String?

        if (character != null) {
            print("Enter a new Name for [ " + character.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new Race for [ " + character.name + " ] : ")
            tempRace = readLine()!!
            print("Enter a new Class for [ " + character.name + " ] : ")
            tempClss = readLine()!!

            if (!tempName.isNullOrEmpty() && !tempRace.isNullOrEmpty() && !tempClss.isNullOrEmpty()) {
                character.name = tempName
                character.race = tempRace
                character.clss = tempClss
                return true
            }
        }
        return false
    }



    fun getId() : Long {
        val strId : String? // String to hold user input
        val searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }


}

