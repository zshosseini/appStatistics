package tapsell.test.appStatistics.utils

import com.ibm.icu.util.Calendar
import com.ibm.icu.util.ULocale
import org.springframework.stereotype.Component
import java.util.*

@Component
class DateTimeUtil {
    val locale = ULocale("@calendar=persian")

    private fun getCalendar(): Calendar {
        return Calendar.getInstance(locale)
    }

    fun getPersianWeekYear(date: Date?): String {
        return formatDate(date)
    }

    fun getPersianWeek(date: Date?): Int {
        val cal = getCalendar()
        cal.firstDayOfWeek=7
        cal.time=date
        return when(cal.get(Calendar.MONTH)>1 && cal.get(Calendar.WEEK_OF_YEAR)==1){
            true ->  53
            false ->  cal.get(Calendar.WEEK_OF_YEAR)
        }

    }

    fun getPersianYear(date: Date?): Int {
        val cal = getCalendar()
        cal.time=date
        return cal.get(Calendar.YEAR)
    }

    fun formatDate(time: Date?): String {
        val cal = getCalendar()
        cal.time=time
        return String.format("%04d-%02d",
                cal.get(Calendar.YEAR),
                cal.get(Calendar.WEEK_OF_YEAR))
    }

    fun gregorianMonthAgo(daysAgo: Int): Date {
        val calendar = java.util.Calendar.getInstance()
        calendar.add(java.util.Calendar.MONTH, -daysAgo)

        return calendar.time
    }

    fun gregorianDaysAgo(daysAgo: Int): Date {
        val calendar = java.util.Calendar.getInstance()
        calendar.add(java.util.Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar.time
    }

    fun gregorianHourAgo(daysAgo: Int): Date {
        val calendar = java.util.Calendar.getInstance()
        calendar.add(java.util.Calendar.HOUR_OF_DAY, -daysAgo)

        return calendar.time
    }
}