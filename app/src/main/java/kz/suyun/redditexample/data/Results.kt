package kz.suyun.redditexample.data

data class Results(
    val info: Info,
    val results: List<Character>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: CharacterLocation,
    val image: String,
    val episode: List<String>? = null,
    val url: String,
    val created: String
)

data class CharacterLocation(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)