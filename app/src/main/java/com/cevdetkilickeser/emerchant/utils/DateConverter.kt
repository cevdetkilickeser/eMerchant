package com.cevdetkilickeser.emerchant.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

object DateConverter {

    fun zoneDateConverter(isoDateTime: String): String {
        var formattedDate = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val zonedDateTime = ZonedDateTime.parse(isoDateTime)
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")
            formattedDate = zonedDateTime.format(formatter)
        } else {
            val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            isoFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = isoFormat.parse(isoDateTime)
            val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
            formattedDate = date?.let { outputFormat.format(it) }.toString()
        }
        return formattedDate
    }
}