package ru.vitalydemidov.og_testapp.presentation.content.viewholder

import android.view.View
import android.widget.TextView
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.appcommon.adapter.AbstractBaseItemViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureResultVM

class FixtureResultViewHolder(itemView: View) : AbstractBaseItemViewHolder<FixtureResultVM>(itemView) {

    private val competitionLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_competition)
    private val venueLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_venue)
    private val homeTeamLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_home_team)
    private val awayTeamLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_away_team)
    private val dateLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_date)

    override fun bindHolder(viewModel: FixtureResultVM) {
        competitionLabel.text = viewModel.competition
        venueLabel.text = viewModel.venue
        homeTeamLabel.text = viewModel.homeTeam
        awayTeamLabel.text = viewModel.awayTeam
        dateLabel.text = viewModel.dateAndTime
    }
}