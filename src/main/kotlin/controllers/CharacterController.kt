import mu.KotlinLogging
import views.CharacterView
import models.CharacterModel
import models.CharacterJSONStore

class CharacterController {


    val characters = CharacterJSONStore()
    val characterView = CharacterView()
    val logger = KotlinLogging.logger {}

    init {
        println(logger.info { "Launching Evans Character Manager Console App" })
        println("Character Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Console App" }
    }

    fun menu() :Int { return characterView.menu() }

    fun add(){
        val aCharacter= CharacterModel()

        if (characterView.addCharacterData(aCharacter))
            characters.create(aCharacter)
        else
            logger.info("Character Not Added")
    }

    fun list() {
        characterView.listCharacters(characters)
    }

    fun delete() {

        characterView.listCharacters(characters)
        val searchId = characterView.getId()
        val character = search(searchId)

        if(character != null)
            characters.delete(character)
        else
            println(logger.info("Character Not Deleted"))
    }

    fun update() {

        characterView.listCharacters(characters)
        val searchId = characterView.getId()
        val aCharacter = search(searchId)

        if(aCharacter != null) {
            if(characterView.updateCharacterData(aCharacter)) {
                characters.update(aCharacter)
                characterView.showCharacter(aCharacter)
                println(logger.info("Character Updated : [ $aCharacter ]"))
            }
            else
                println(logger.info("Character Not Updated"))
        }
        else
            println("Character Not Updated...")
    }

    fun search() {
        val aCharacter = search(characterView.getId())!!
        characterView.showCharacter(aCharacter)
    }


    fun search(id: Long) : CharacterModel? {
        val foundCharacter = characters.findOne(id)
        return foundCharacter
    }
}