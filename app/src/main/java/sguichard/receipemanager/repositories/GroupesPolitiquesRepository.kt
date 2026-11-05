package sguichard.receipemanager.repositories

import android.util.Log
import sguichard.receipemanager.data.GroupePolitique
import sguichard.receipemanager.datasource.api.NetworkDataSource

object GroupesPolitiquesRepository {

    val networkService = NetworkDataSource.groupesPolitiquesService

    suspend fun getGroupesPolitiques(): List<GroupePolitique>{
        val groupesPolitiques = networkService.getGroupesPolitiques().data.groupesPolitiquesList
        Log.v("getGroupesPolitiques", groupesPolitiques.toString())
        return groupesPolitiques
    }

    suspend fun getGroupePolitiqueDetail(abbr : String): GroupePolitique{
        val groupePolitique = networkService.getGroupePolitique(abbr).data.groupePolitique
        return groupePolitique
    }
}