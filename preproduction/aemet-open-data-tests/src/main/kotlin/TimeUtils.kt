package org.example

import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

/**
 * Time utils.
 */
object TimeUtils {
    /**
     * Gets the current time.
     *
     * @return Returns the current time as [LocalTime] on the current system time zone.
     */
    fun now(): LocalTime = Clock.System.now()
        .toLocalDateTime(
            TimeZone.currentSystemDefault()
        ).time
}