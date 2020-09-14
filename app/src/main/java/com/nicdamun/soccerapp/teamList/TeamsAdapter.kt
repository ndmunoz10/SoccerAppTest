package com.nicdamun.soccerapp.teamList

import androidx.core.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicdamun.core.extensions.tryCast
import com.nicdamun.core.models.TeamModel
import com.nicdamun.soccerapp.databinding.TeamItemBinding
import com.nicdamun.soccerapp.extensions.loadImage
import com.nicdamun.soccerapp.utils.TeamDiffCallback

class TeamsAdapter(
    private val onItemClicked: (teamModel: TeamModel, pairs: List<Pair<View, String>>) -> Unit
): ListAdapter<TeamModel, RecyclerView.ViewHolder>(TeamDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.tryCast<TeamViewHolder>()?.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(
            TeamItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    inner class TeamViewHolder(
        private val itemBinding: TeamItemBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(teamModel: TeamModel) {
            itemBinding.run {
                tvTeamNameValue.text = teamModel.name
                tvStadiumValue.text = teamModel.stadiumName
                ivTeamBadge.loadImage(teamModel.badge)
                root.setOnClickListener { startTeamDetailActivity(this, teamModel) }
            }
        }

        private fun startTeamDetailActivity(itemBinding: TeamItemBinding, teamModel: TeamModel) {
            val teamBadgePair = Pair.create<View, String>(itemBinding.ivTeamBadge, "team_badge_transition")
            onItemClicked(teamModel, listOf(teamBadgePair))
        }

    }
}