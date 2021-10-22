import models.CharacterModel
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CharacterMemStore : CharacterStore {

    val characters = ArrayList<CharacterModel>()

    override fun findAll(): List<CharacterModel> {
        return characters
    }

    override fun findOne(id: Long) : CharacterModel? {
        val foundCharacter: CharacterModel? = characters.find { p -> p.id == id }
        return foundCharacter
    }

    override fun create(character: CharacterModel) {
        character.id = getId()
        characters.add(character)
        logAll()
    }

    override fun update(character: CharacterModel) {
        val foundCharacter = findOne(character.id)
        if (foundCharacter != null) {
            foundCharacter.name = character.name
            foundCharacter.race = character.race
            foundCharacter.clss = character.clss
        }
    }

    internal fun logAll() {
        characters.forEach { logger.info("${it}") }
    }
}