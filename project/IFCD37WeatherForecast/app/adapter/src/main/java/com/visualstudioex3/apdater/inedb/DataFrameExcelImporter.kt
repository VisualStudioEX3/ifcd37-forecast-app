package com.visualstudioex3.apdater.inedb

import com.visualstudioex3.apdater.inedb.exceptions.DataFrameExcelImporterException
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema

/**
 * Dataframe importer for EXCEL files.
 */
internal interface DataFrameExcelImporter {
    /**
     * Imports an EXCEL file as [DataFrame] object.
     *
     * @param TExcelRowSchema The [DataSchema] schema interface that defines the row structure in
     * the EXCEL sheet to import.
     * @param resourceName Path to the resource XLS/XLSX file to import.
     * @param sheetName Optional. EXCEL sheet name to import. Sets to null to import the first
     * sheet. By default is null.
     * @param skipRows The number of rows to skip before start to import.
     * @param stringColumns Optional. A list of columns that must be imported as [String] columns.
     * The list can be contains a list of columns separated by comma (e.g.: "A,B,C") and also
     * supports define ranges (e.g. "A:D"). Set to null to left [DataFrame] infer the type of the
     * column. By default is null.
     *
     * @return Returns a [DataFrame]<[TExcelRowSchema]> object.
     *
     * @throws DataFrameExcelImporterException Throws for one of the following inner exceptions:
     * - [NoSuchElementException]: If resource [resourceName] not found.
     * - Any derived [DataFrame] exceptions on EXCEL import operation.
     */
    fun <TExcelRowSchema> importExcelAsDataFrame(
        resourceName: String,
        sheetName: String? = null,
        skipRows: Int,
        stringColumns: String? = null
    ): DataFrame<TExcelRowSchema>
}
