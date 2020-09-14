package com.nicdamun.soccerapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.nicdamun.core.models.TeamModel

class TeamDiffCallback: DiffUtil.ItemCallback<TeamModel>() {

    override fun areContentsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean {
        return oldItem.id == newItem.id
    }
}