package com.nicdamun.soccerapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.nicdamun.core.models.EventModel

class EventDiffCallback: DiffUtil.ItemCallback<EventModel>() {

    override fun areContentsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
        return oldItem.name == newItem.name
    }
}