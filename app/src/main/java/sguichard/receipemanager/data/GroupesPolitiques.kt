package sguichard.receipemanager.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupesPolitiques(@SerialName("groups") val groupesPolitiquesList : List<GroupePolitique>)

@Serializable
data class GroupePolitique(
    val name: String,
    val manaCost: String? = null,
    var colors: ArrayList<String> = arrayListOf(),
    var type: String? = null,
    var rarity: String? = null
)
