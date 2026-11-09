package sguichard.receipemanager.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataGroupD(@SerialName("data") val data : DeputesData)

@Serializable
data class DataSingleD(@SerialName("data") val data : DeputeData)

@Serializable
data class DeputesData(@SerialName("results") val deputesList: List<Depute>)

@Serializable
data class DeputeData(@SerialName("deputy") val depute : Depute)

@Serializable
data class Depute(
    val uid: String,
    val prenom: String,
    val nom: String,
    @SerialName("circ_num")val numCirco: Int,
    @SerialName("circ_departement") val departement: String,
    @SerialName("groupe_abrev") val groupeAbbr: String,
    @SerialName("groupe_libelle") val groupeNom: String
)