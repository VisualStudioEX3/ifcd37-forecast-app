package com.visualstudioex3.apdater.inedb.repositories

import com.visualstudioex3.apdater.inedb.DataFrameExcelImporter
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import org.jetbrains.kotlinx.dataframe.api.firstOrNull

class IneAutonomousCommunityRepositoryImplementation(
    excelImporter: DataFrameExcelImporter
): IneAutonomousCommunityRepository {
    /** @suppress  */
    @Suppress("PropertyName", "unused")
    @DataSchema
    interface DataRowSchema {
        val CODIGO: String
        val LITERAL: String
    }

    private val provinces: DataFrame<DataRowSchema> = excelImporter.importExcelAsDataFrame(
        fileName = "codccaa.xls",
        sheetName = "Hoja1",
        skipRows = 1,
        stringColumns = "A"
    )

    override fun getName(
        code: String
    ): String = require(code.isNotBlank()) {
        "The code can not be empty or blank string."
    }.run {
        provinces
            .firstOrNull {
                CODIGO == code
            }?.LITERAL
            ?: throw NoSuchElementException("Autonomous Community by code '$code' not found.")
    }
}
