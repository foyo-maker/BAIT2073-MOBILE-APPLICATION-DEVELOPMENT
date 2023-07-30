package com.example.bait2073mobileapplicationdevelopment.models

import java.time.LocalDate
import java.time.LocalTime


class History(var name: String, var date: LocalDate, var time: LocalTime) {

    companion object {
        var historyList = ArrayList<History>()
        fun historyForDate(date: LocalDate): ArrayList<History> {
            val histories = ArrayList<History>()
            for (history in historyList) {
                if (history.date == date) histories.add(history)
            }
            return histories
        }
    }
}
