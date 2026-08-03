package com.visualstudioex3.apdater.inedb.repositories

import com.visualstudioex3.apdater.inedb.DataFrameExcelImporter
import com.visualstudioex3.apdater.inedb.models.IneMunicipalityData
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.api.map

internal class IneMunicipalityRepositoryImplementation(
    excelImporter: DataFrameExcelImporter
): IneMunicipalityRepository {
    /** @suppress  */
    @Suppress("PropertyName", "unused")
    @DataSchema
    interface DataRowSchema {
        val CMUN: String
        val CODAUTO: String
        val CPRO: String
        val DC: String
        val NOMBRE: String
    }

    private val municipalities: DataFrame<DataRowSchema> = excelImporter.importExcelAsDataFrame(
        resourceName = "diccionario26.xlsx",
        sheetName = "dic25",
        skipRows = 1,
        stringColumns = "C:D"
    )

    override fun findByName(
        name: String
    ): List<IneMunicipalityData> =
        require(name.isNotBlank()) {
            "The name can not be empty or blank string."
        }.run {
            municipalities
                .filter {
                    NOMBRE.contains(name, ignoreCase = true)
                }.map {
                    IneMunicipalityData(
                        autnomousCommunityCode = CODAUTO,
                        provinceCode = CPRO,
                        municipalityCode = CMUN,
                        controlDigit = DC,
                        name = NOMBRE
                    )
                }
        }
}
