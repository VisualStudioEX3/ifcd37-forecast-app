package com.visualstudioex3.apdater.inedb.exceptions

import com.visualstudioex3.apdater.inedb.DataFrameExcelImporter

/**
 * [DataFrameExcelImporter] service exception.
 *
 * @param message Exception message.
 * @param e Inner exception.
 */
class DataFrameExcelImporterException(
    message: String,
    e: Exception
): RuntimeException("$message: $e") {
}
