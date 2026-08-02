package org.example.ine.services

import org.example.ResourceUtils
import org.example.ine.models.IneCityData
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.cast
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.api.firstOrNull
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.io.StringColumns
import org.jetbrains.kotlinx.dataframe.io.readExcel

class IneService
    : IIneService {
    private val cities: DataFrame<IneCityExcelRowSchema> = loadExcelFile(
        resourceName = "diccionario26.xlsx",
        sheetName = "dic25",
        skipRows = 1,
        stringColumns = "C:D"
    )
    private val states: DataFrame<IneStateExcelRowSchema> = loadExcelFile(
        resourceName = "codprov.xls",
        sheetName = "Hoja1",
        skipRows = 1,
        stringColumns = "A"
    )

    override suspend fun findCitiesByName(
        name: String
    ): List<IneCityData> =
        require(name.isNotBlank()) {
            "The name can not be empty or blank string."
        }.run {
            cities
                .filter {
                    NOMBRE.contains(name, ignoreCase = true)
                }.map {
                    IneCityData(
                        autnomousCommunityCode = CODAUTO,
                        stateCode = CPRO,
                        cityCode = CMUN,
                        controlDigit = DC,
                        name = NOMBRE
                    )
                }
        }

    override suspend fun getStateNameByCode(code: String): String =
        require(code.isNotBlank()) {
            "The code can not be empty or blank string."
        }.run {
            states
                .firstOrNull {
                    CODIGO == code
                }?.LITERAL
                ?: throw NoSuchElementException("State by code '$code' not found.")
        }

    private inline fun <reified TExcelRowSchema> loadExcelFile(
        resourceName: String,
        sheetName: String? = null,
        skipRows: Int,
        stringColumns: String? = null
    ): DataFrame<TExcelRowSchema> {
        try {
            val url = ResourceUtils.getResource(resourceName)
                ?: error("Resource with id '$resourceName' not found.")

            return DataFrame
                .readExcel(
                    url,
                    sheetName,
                    skipRows,
                    stringColumns = if (stringColumns != null)
                        StringColumns(stringColumns)
                    else
                        null
                ).cast<TExcelRowSchema>(verify = true)
        } catch (e: Exception) {
            error("Error loading EXCEL file. ${e.message}")
        }
    }
}