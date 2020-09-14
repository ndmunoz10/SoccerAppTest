package com.nicdamun.soccerapp.teamDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicdamun.core.extensions.tryCast
import com.nicdamun.core.models.EventModel
import com.nicdamun.soccerapp.R
import com.nicdamun.soccerapp.databinding.EventItemBinding
import com.nicdamun.soccerapp.utils.EventDiffCallback

class EventsAdapter: ListAdapter<EventModel, RecyclerView.ViewHolder>(EventDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.tryCast<EventViewHolder>()?.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventViewHolder(
            EventItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    class EventViewHolder(
        private val eventItemBinding: EventItemBinding
    ): RecyclerView.ViewHolder(eventItemBinding.root) {

        fun bind(eventModel: EventModel) {
            eventItemBinding.run {
                val context = root.context
                tvEventSeason.text = context.getString(R.string.league_x, eventModel.leagueName)
                tvEventName.text = eventModel.name
                tvDateTime.text = context.getString(R.string.watch_on_x, eventModel.dateEvent)
            }
        }
    }
}