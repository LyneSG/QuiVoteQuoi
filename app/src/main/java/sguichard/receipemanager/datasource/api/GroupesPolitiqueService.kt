package sguichard.receipemanager.datasource.api

import retrofit2.http.GET
import retrofit2.http.Path
import sguichard.receipemanager.data.DataSingleGP
import sguichard.receipemanager.data.DataGroupGP

interface GroupesPolitiqueService {

    @GET("groupes")
    suspend fun getGroupesPolitiques(): DataGroupGP

    @GET("groupes/{abbr}")
    suspend fun getGroupePolitiqueByAbbr(@Path("abbr") abbrName: String): DataSingleGP
}