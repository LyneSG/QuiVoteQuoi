package sguichard.receipemanager.datasource.api

import retrofit2.http.GET
import retrofit2.http.Path
import sguichard.receipemanager.data.DataGroup
import sguichard.receipemanager.data.DataGroups

interface GroupesPolitiqueService {

    @GET("groupes")
    suspend fun getGroupesPolitiques(): DataGroups

    @GET("groupes/{abbr}")
    suspend fun getGroupePolitique(@Path("abbr") abbrName: String): DataGroup
}