package com.sphtechapp.myapplicationsph2.backend

import com.sphtechapp.myapplicationsph2.activities.main.data.DataUsageResponse
import retrofit2.http.*

interface ServiceUtil {

    @GET("api/action/datastore_search")
    suspend fun getList(
        @Query("resource_id") resourceId: String
    ): DataUsageResponse
}