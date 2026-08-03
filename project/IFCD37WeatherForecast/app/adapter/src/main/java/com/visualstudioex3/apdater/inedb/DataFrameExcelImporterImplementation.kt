package com.visualstudioex3.apdater.inedb

import android.content.Context
import com.visualstudioex3.apdater.inedb.exceptions.DataFrameExcelImporterException
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.cast
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.io.StringColumns
import org.jetbrains.kotlinx.dataframe.io.readExcel

internal class DataFrameExcelImporterImplementation(
    val context: Context
) : DataFrameExcelImporter
{
    override fun <TExcelRowSchema> importExcelAsDataFrame(
        fileName: String,
        sheetName: String?,
        skipRows: Int,
        stringColumns: String?
    ): DataFrame<TExcelRowSchema> =
        try {
            DataFrame
                .readExcel(
                    inputStream = context.assets.open(fileName),
                    sheetName,
                    skipRows,
                    stringColumns = if (stringColumns != null)
                        StringColumns(stringColumns)
                    else
                        null,
                    parseEmptyAsNull = false
                ).filter { // Removes any row with null or empty/blank string values:
                    !it.values().any { value ->
                        if (value is String)
                            value.isBlank()
                        else
                            value == null
                    }
                }.cast()
        } catch (e: Exception) {
            throw DataFrameExcelImporterException("Error loading EXCEL file.", e)
        }
}
