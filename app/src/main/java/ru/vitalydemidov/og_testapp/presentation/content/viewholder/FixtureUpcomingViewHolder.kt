package ru.vitalydemidov.og_testapp.presentation.content.viewholder

import android.view.View
import android.widget.TextView
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.appcommon.adapter.AbstractBaseItemViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureUpcomingVM

class FixtureUpcomingViewHolder(itemView: View) : AbstractBaseItemViewHolder<FixtureUpcomingVM>(itemView) {

    private val competitionLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_competition)
    private val venueLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_venue)
    private val homeTeamLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_home_team)
    private val awayTeamLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_away_team)
    private val dateLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_date)
    private val dayOfMonth: TextView = itemView.findViewById(R.id.item_fixture_upcoming_day_of_month)
    private val dayOfWeek: TextView = itemView.findViewById(R.id.item_fixture_upcoming_day_of_week)
    private val postponedBanner: TextView = itemView.findViewById(R.id.item_fixture_upcoming_postponed_banner)

    override fun bindHolder(viewModel: FixtureUpcomingVM) {
        competitionLabel.text = viewModel.competition
        venueLabel.text = viewModel.venue
        homeTeamLabel.text = viewModel.homeTeam
        awayTeamLabel.text = viewModel.awayTeam
        dateLabel.text = viewModel.dateAndTime
        dayOfMonth.text = viewModel.dayOfMonth
        dayOfWeek.text = viewModel.dayOfWeek
        postponedBanner.visibility = if (viewModel.postponedBannerVisible) View.VISIBLE else View.INVISIBLE
    }
}