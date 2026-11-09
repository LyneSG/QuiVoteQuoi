package sguichard.receipemanager.repositories

import android.util.Log
import sguichard.receipemanager.data.Depute
import sguichard.receipemanager.datasource.api.NetworkDataSource

object DeputesRepository {

    val networkService = NetworkDataSource.deputesService

    suspend fun getDeputes(): Result<List<Depute>>{
        try {
            val deputes = networkService.getDeputes().data.deputesList
            return Result.success(deputes)
        } catch (e: Exception){
            Log.e("getDeputes", e.toString())
            return Result.failure(e)
        }
    }

    suspend fun getDeputeByUID(uid: String): Result<Depute>{
        try {
            val depute = networkService.getDeputeByUID(uid).data.depute
            return Result.success(depute)
        } catch(e: Exception){
            Log.e("getDeputeByUID", e.toString())
            return Result.failure(e)
        }
    }
}