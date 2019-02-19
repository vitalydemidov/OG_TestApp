package ru.vitalydemidov.og_testapp.presentation.content

import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.data.model.Fixture

@UiThread
class FixturesListAdapter : RecyclerView.Adapter<FixturesListAdapter.FixtureViewHolder>() {

    internal var dataList: List<Fixture> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_fixture_upcoming, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val competitionLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_competition)
        private val venueLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_venue)
        private val homeTeamLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_home_team)
        private val awayTeamLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_away_team)
        private val dateLabel: TextView = itemView.findViewById(R.id.item_fixture_upcoming_date)

        fun bind(fixture: Fixture) {
            competitionLabel.text = fixture.competitionStage.competition.name
            venueLabel.text = fixture.venue.name
            homeTeamLabel.text = fixture.homeTeam.name
            awayTeamLabel.text = fixture.awayTeam.name
            dateLabel.text = fixture.date
        }

    }

}