package com.belajar.aplikasicuaca.utils

import java.text.SimpleDateFormat
import java.util.Date

class Formatter {

    fun dateFormatter(timeStamp: Int): String {
        val sdf = SimpleDateFormat("EEEE, MMMM d")
        val date = Date(timeStamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun hoursFormat(timeStamp: Int): String {
        val sdf = SimpleDateFormat("hh:mm")
        val date = Date(timeStamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun dayFormat(timeStamp: Int): String {
        val sdf = SimpleDateFormat("EEEE")
        val date = Date(timeStamp.toLong() * 1000)
        return sdf.format(date)
    }

}