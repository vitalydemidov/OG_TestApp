package ru.vitalydemidov.og_testapp.domain

import android.content.Context
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import io.reactivex.functions.Function
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.appcommon.BaseItemMapper
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.data.model.Score
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureResultVM
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureUpcomingVM
import ru.vitalydemidov.og_testapp.util.DataParseTemplate
import ru.vitalydemidov.og_testapp.util.FixtureType
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.DAY_OF_WEEK

class FixtureMapper(
    context: Context,
    private val baseItemMapper: BaseItemMapper
) : Function<Fixture, BaseItem<*>> {

    @ColorInt
    private val teamWinnerColor = ContextCompat.getColor(context, R.color.textColorPrimary)

    @ColorInt
    private val teamDefaultColor = ContextCompat.getColor(context, R.color.textColorSecondary)

    @ColorInt
    private val dateDefaultColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)

    @ColorInt
    private val datePostponedColor = ContextCompat.getColor(context, R.color.textColorRed)

    private val dayOfMonthDateFormat = SimpleDateFormat(DataParseTemplate.DAY_OF_MONTH.format, Locale.getDefault())
    private val dayOfWeekDateFormat = SimpleDateFormat(DataParseTemplate.DAY_OF_WEEK.format, Locale.getDefault())
    private val dateAndTimeDateFormat = SimpleDateFormat(DataParseTemplate.DATE_AND_TIME.format, Locale.getDefault())
    private val inputDateAndTimeDateFormat = SimpleDateFormat(DataParseTemplate.INPUT_DATE_AND_TIME.format, Locale.getDefault())

    private val calendar = Calendar.getInstance()

    override fun apply(fixture: Fixture): BaseItem<*> {
        return mapByType(fixture)
    }

    fun applyToList(fixtures: List<Fixture>): List<BaseItem<in Nothing>> {
        val baseItems = ArrayList<BaseItem<*>>(fixtures.size)
        for (fixture in fixtures) {
            baseItems.add(apply(fixture))
        }
        return baseItems
    }

    private fun mapByType(fixture: Fixture): BaseItem<*> {
        return when (fixture.type) {
            FixtureType.UPCOMING.type -> baseItemMapper.toBaseItem(R.id.fixture_upcoming_item_id, mapFixtureUpcoming(fixture))
            FixtureType.FINAL.type -> baseItemMapper.toBaseItem(R.id.fixture_final_item_id, mapFixtureFinal(fixture))
            else -> throw IllegalArgumentException("Unknown fixture type!")
        }
    }

    private fun mapFixtureUpcoming(fixture: Fixture): FixtureUpcomingVM {
        return FixtureUpcomingVM(
            fixture.competitionStage.competition.name,
            fixture.venue.name,
            prepareDateAndTime(fixture),
            fixture.homeTeam.name,
            fixture.awayTeam.name,
            fixture.state,
            prepareDayOfMonth(fixture.date),
            prepareDayOfWeek(fixture.date)
        )
    }

    private fun mapFixtureFinal(fixture: Fixture): FixtureResultVM {
        return FixtureResultVM(
            fixture.competitionStage.competition.name,
            fixture.venue.name,
            fixture.date,
            fixture.homeTeam.name,
            fixture.awayTeam.name,
            prepareHomeScore(fixture.score!!),
            prepareAwayScore(fixture.score)
        )
    }

    private fun prepareDayOfMonth(date: String): String {
        calendar.time = inputDateAndTimeDateFormat.parse(date)
        return calendar.get(DAY_OF_MONTH).toString()
    }

    private fun prepareDayOfWeek(date: String): String {
        calendar.time = inputDateAndTimeDateFormat.parse(date)
        return calendar.get(DAY_OF_WEEK).toString()
    }

    private fun prepareDateAndTime(fixture: Fixture): Spannable {
        val date = inputDateAndTimeDateFormat.parse(fixture.date)
        calendar.time = date
        val formattedDate = dateAndTimeDateFormat.format(date)

        val spannableString = SpannableString(formattedDate)
        spannableString.setSpan(
            ForegroundColorSpan(if (fixture.state.equals("postponed")) datePostponedColor else dateDefaultColor),
            0,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

    private fun prepareHomeScore(score: Score): Spannable {
        val spannableString = SpannableString(score.home)
        spannableString.setSpan(
            ForegroundColorSpan(if (score.winner.equals("home")) teamWinnerColor else teamDefaultColor),
            0,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

    private fun prepareAwayScore(score: Score): Spannable {
        val spannableString = SpannableString(score.away)
        spannableString.setSpan(
            ForegroundColorSpan(if (score.winner.equals("away")) teamWinnerColor else teamDefaultColor),
            0,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

}