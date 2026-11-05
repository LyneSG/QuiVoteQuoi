package sguichard.receipemanager.datasource.api

import retrofit2.http.GET
import retrofit2.http.Path
import sguichard.receipemanager.data.GroupePolitique
import sguichard.receipemanager.data.GroupesPolitiques

interface GroupesPolitiqueService {

    @GET("groupes")
    suspend fun getGroupesPolitiques(): GroupesPolitiques

    @GET("groupes/{abbr}")
    suspend fun getGroupePolitique(@Path("abbr") abbrName: String): GroupePolitique
}