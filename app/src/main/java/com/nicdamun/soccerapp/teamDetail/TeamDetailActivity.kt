package com.nicdamun.soccerapp.teamDetail

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import com.nicdamun.core.models.TeamModel
import com.nicdamun.soccerapp.R
import com.nicdamun.soccerapp.common.BaseActivity
import com.nicdamun.soccerapp.databinding.ActivityTeamDetailBinding
import com.nicdamun.soccerapp.di.viewModelFactory.DaggerViewModelFactory
import com.nicdamun.soccerapp.extensions.loadImage
import com.nicdamun.soccerapp.extensions.showOrHide
import javax.inject.Inject

class TeamDetailActivity: BaseActivity() {

    companion object {
        const val TEAM_MODEL_KEY = "team_model_key"
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var adapter: EventsAdapter
    private lateinit var binding: ActivityTeamDetailBinding
    private var teamModel: TeamModel? = null
    private val viewModel: TeamDetailViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtras()
        setupUI()
        setupObservers()
        fetchEvents()
    }

    private fun changeLoadingState(isLoading: Boolean) {
        binding.run {
            rvEvents.showOrHide(show = !isLoading)
            includeShimmerContainer.root.showOrHide(show = isLoading)
        }
    }

    private fun fetchEvents() {
        changeLoadingState(isLoading = true)
        teamModel?.id?.let { teamId -> viewModel.fetchTeamEventsById(teamId) }
    }

    private fun fillViews() {
        binding.run {
            tvTeamName.text = teamModel?.name
            ivTeamBadge.loadImage(teamModel?.badge)
            tvTeamDescription.text = teamModel?.description
            tvTeamFoundationYear.text = getString(R.string.founded_on_x, teamModel?.formedYear)
            ivTeamJersey.loadImage(teamModel?.jersey.orEmpty())
            tvWebsite.text = getString(R.string.underlined_text, teamModel?.website)
            tvTwitter.text = getString(R.string.underlined_text, teamModel?.twitter)
            tvInstagram.text = getString(R.string.underlined_text, teamModel?.instagram)
            tvFacebook.text = getString(R.string.underlined_text, teamModel?.facebook)
        }
    }

    private fun getExtras() {
        teamModel = intent.extras?.getParcelable(TEAM_MODEL_KEY)
    }

    private fun initializeEventList() {
        adapter = EventsAdapter()
        binding.rvEvents.apply {
            adapter = this@TeamDetailActivity.adapter
        }
    }

    private fun setupClickListeners() {
        binding.run {
            tvWebsite.setOnClickListener { startWebView(tvWebsite.text.toString()) }
            tvTwitter.setOnClickListener {startWebView(tvTwitter.text.toString())  }
            tvInstagram.setOnClickListener { startWebView(tvInstagram.text.toString()) }
            tvFacebook.setOnClickListener { startWebView(tvFacebook.text.toString()) }
        }
    }

    private fun setupObservers() {
        viewModel.teamEventsObs().observe(this, {
            adapter.submitList(it)
            changeLoadingState(isLoading = false)
        })
    }

    private fun setupUI() {
        fillViews()
        initializeEventList()
        setupClickListeners()
    }

    private fun startWebView(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse("https://$url"))
    }
}