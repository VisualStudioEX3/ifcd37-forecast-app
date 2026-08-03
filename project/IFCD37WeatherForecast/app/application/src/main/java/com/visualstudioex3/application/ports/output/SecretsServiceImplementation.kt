package com.visualstudioex3.application.ports.output

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

internal class SecretsServiceImplementation(
    private val context: Context
): SecretsService {
    private val appInfo: ApplicationInfo = context.packageManager.getApplicationInfo(
        context.packageName,
        PackageManager.GET_META_DATA
    )

    override fun getString(key: String): String? = appInfo.metaData
        .getString(key)
}
