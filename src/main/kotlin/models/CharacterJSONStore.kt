package models

import CharacterStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import helpers.*
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "characters.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<CharacterModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CharacterJSONStore : CharacterStore {

    var characters = mutableListOf<CharacterModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CharacterModel> {
        return characters
    }

    override fun findOne(id: Long) : CharacterModel? {
        val foundCharacter: CharacterModel? = characters.find { p -> p.id == id }
        return foundCharacter
    }

    override fun create(character: CharacterModel) {
        character.id = generateRandomId()
        characters.add(character)
        serialize()
    }

    fun delete(character: CharacterModel){
        characters.remove(character)
        serialize()
    }

    override fun update(character: CharacterModel) {
        val foundcharacter = findOne(character.id)
        if (foundcharacter != null) {
            foundcharacter.name = character.name
            foundcharacter.race = character.race
            foundcharacter.clss = character.clss
        }
        serialize()
    }

    internal fun logAll() {
        characters.forEach {
            println("ID:" + "${it.id}")
            println("Name: " + "${it.name}" + "\n")
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(characters, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        characters = Gson().fromJson(jsonString, listType)
    }
}