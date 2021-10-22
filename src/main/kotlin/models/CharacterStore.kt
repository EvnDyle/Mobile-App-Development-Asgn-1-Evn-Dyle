import models.CharacterModel

interface CharacterStore {
    fun findAll(): List<CharacterModel>
    fun findOne(id: Long): CharacterModel?
    fun create(character: CharacterModel)
    fun update(character: CharacterModel)
}