package com.visualstudioex3.ifcd37weatherforecast.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Header title.
 *
 * Shows a text formated as headline medium size with a horizontal divider below.
 *
 * @param text Text to show.
 */
@Composable
fun HeaderTitle(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.fillMaxWidth()
    )
    HorizontalDivider()
}
