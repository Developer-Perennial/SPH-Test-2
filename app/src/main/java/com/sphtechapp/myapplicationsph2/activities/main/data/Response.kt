package com.sphtechapp.myapplicationsph2.activities.main.data

data class DataUsageResponse(
    val help: String,
    val success: Boolean,
    val result: ResultData
)

data class ResultData(
    val resourceId: String,
    val fields: List<FieldsData>,
    val records: List<RecordsData>,
    val links: LinksData,
    val limit: Int,
    val total: Int
)

data class FieldsData(
    val type: String,
    val id: String
)

data class RecordsData(
    val _id: Int,
    val volume_of_mobile_data: String,
    val quarter: String,
    var year: Int,
    var total_volume: Double = 0.0,
    var decreased: Boolean = false
) {
    constructor(id: Int, volumeOfMobileData: String, quarter: String) : this(
        id,
        volumeOfMobileData,
        quarter,
        0,
        0.0,
        false
    )
}

data class LinksData(
    val start: String,
    val next: String
)