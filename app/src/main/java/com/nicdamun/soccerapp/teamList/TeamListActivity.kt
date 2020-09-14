package com.nicdamun.soccerapp.teamList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.nicdamun.core.models.TeamModel
import com.nicdamun.soccerapp.R
import com.nicdamun.soccerapp.common.BaseActivity
import com.nicdamun.soccerapp.constants.GeneralConstants.BUNDESLIGA_LEAGUE_NAME
import com.nicdamun.soccerapp.constants.GeneralConstants.DEFAULT_LEAGUE_NAME
import com.nicdamun.soccerapp.constants.GeneralConstants.PREMIER_LEAGUE_NAME
import com.nicdamun.soccerapp.databinding.ActivityTeamListBinding
import com.nicdamun.soccerapp.di.viewModelFactory.DaggerViewModelFactory
import com.nicdamun.soccerapp.extensions.showOrHide
import com.nicdamun.soccerapp.teamDetail.TeamDetailActivity
import com.nicdamun.soccerapp.teamDetail.TeamDetailActivity.Companion.TEAM_MODEL_KEY
import javax.inject.Inject

class TeamListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var binding: ActivityTeamListBinding
    private lateinit var teamsAdapter: TeamsAdapter
    private val viewModel: TeamListViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObservers()
        fetchTeamsByLeagueName(DEFAULT_LEAGUE_NAME)
    }

    private fun changeLoadingState(isLoading: Boolean) {
        binding.run {
            rvTeamList.showOrHide(show = !isLoading)
            includeShimmerContainer.root.showOrHide(show = isLoading)
        }
    }

    private fun fetchTeamsByLeagueName(leagueName: String) {
        changeLoadingState(isLoading = true)
        viewModel.getTeamsByLeagueName(leagueName)
    }

    private fun initializeTeamList() {
        teamsAdapter = TeamsAdapter { teamModel, pairs -> onItemSelected(teamModel, pairs) }
        binding.rvTeamList.apply {
            adapter = this@TeamListActivity.teamsAdapter
        }
    }

    private fun onItemSelected(teamModel: TeamModel, pairs: List<Pair<View, String>>) {
        val intent = Intent(this, TeamDetailActivity::class.java)
        intent.putExtra(TEAM_MODEL_KEY, teamModel)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *pairs.toTypedArray())
        startActivity(intent, options.toBundle())
    }

    private fun setupChipChangedListener() {
        binding.cgTeamsFilters.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.cp_bundesliga -> fetchTeamsByLeagueName(BUNDESLIGA_LEAGUE_NAME)
                R.id.cp_premier_league -> fetchTeamsByLeagueName(PREMIER_LEAGUE_NAME)
                else -> fetchTeamsByLeagueName(DEFAULT_LEAGUE_NAME)
            }
        }
    }

    private fun setupObservers() {
        viewModel.getTeamObs().observe(this, { teams ->
            teamsAdapter.submitList(teams)
            changeLoadingState(isLoading = false)
        })
    }

    private fun setupUI() {
        initializeTeamList()
        setupChipChangedListener()
    }
}