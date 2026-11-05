package sguichard.receipemanager.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataGroups(@SerialName("data") val data : GroupesPolitiques)

@Serializable
data class DataGroup(@SerialName("data") val data : GroupePolitiqueData)

@Serializable
data class GroupesPolitiques(@SerialName("groups") val groupesPolitiquesList : List<GroupePolitique>)

@Serializable
data class GroupePolitiqueData(@SerialName("groupe") val groupePolitique : GroupePolitique)

@Serializable
data class GroupePolitique(
    @SerialName("uid") val uid: String,
    @SerialName("abbr") val abbr: String,
    @SerialName("legislature") val legislature: Int,
    @SerialName("libelle")val name: String,
    @SerialName("effectif")val effectif: Int,
)
