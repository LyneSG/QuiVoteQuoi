package sguichard.receipemanager.repositories

import android.util.Log
import sguichard.receipemanager.data.Depute
import sguichard.receipemanager.data.GroupePolitique
import sguichard.receipemanager.datasource.api.NetworkDataSource

object GroupesPolitiquesRepository {

    val networkService = NetworkDataSource.groupesPolitiquesService

    suspend fun getGroupesPolitiques(): Result<List<GroupePolitique>>{
        try{
            val groupesPolitiques = networkService.getGroupesPolitiques().data.groupesPolitiquesList
            Log.v("getGroupesPolitiques", groupesPolitiques.toString())
            return Result.success(groupesPolitiques)
        } catch (e: Exception){
            Log.e("getGroupesPolitiques", e.toString())
            return Result.failure(e)
        }

    }

    suspend fun getGroupePolitiqueDetail(abbr : String): Result<GroupePolitique>{
        try {
            val groupePolitique = networkService.getGroupePolitiqueByAbbr(abbr).data.groupePolitique
            return Result.success(groupePolitique)
        } catch (e: Exception){
            Log.e("getGroupePolitiqueDetail", e.toString())
            return Result.failure(e)
        }

    }

}