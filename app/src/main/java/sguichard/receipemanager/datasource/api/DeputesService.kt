package sguichard.receipemanager.datasource.api

import retrofit2.http.GET
import retrofit2.http.Path
import sguichard.receipemanager.data.DataGroupD
import sguichard.receipemanager.data.DataSingleD

interface DeputesService {

    @GET("deputes")
    suspend fun getDeputes(): DataGroupD

    @GET("deputes/{uid}")
    suspend fun getDeputeByUID(@Path("uid") uid: String): DataSingleD
}