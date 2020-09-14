package com.nicdamun.repository.teams

import com.nicdamun.core.dto.BaseTeamResponse
import com.nicdamun.core.dto.TeamDTO
import com.nicdamun.network.api.SportsAPI
import com.nicdamun.repository.helpers.Result
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import retrofit2.Response

class TeamRemoteDataSourceTest {

    private val mockSportsAPI = mock(SportsAPI::class.java)
    private val teamRemoteDataSource = TeamsRemoteDataSource(mockSportsAPI)

    private val successfulResponse = BaseTeamResponse(
        teams = listOf(
            TeamDTO(
                team = "Real Madrid",
                twitter = "twitter.com",
                facebook = "facebook.com",
                instagram = "instagram.com",
                id = "1",
                description = "description",
                badge = "badge.png",
                banner = "banner.png",
                formedYear = 1923,
                jersey = "jersey.png",
                stadium = "Bernabeu",
                website = "realmadrid.com"
            ),
            TeamDTO(
                team = "Barcelona",
                twitter = "twitter.com",
                facebook = "facebook.com",
                instagram = "instagram.com",
                id = null,
                description = "description",
                badge = "badge.png",
                banner = "banner.png",
                formedYear = 1924,
                jersey = "jersey.png",
                stadium = "Camp Nou",
                website = "barcelona.com"
            ),
            TeamDTO(
                team = "Atletico Madrid",
                twitter = "twitter.com",
                facebook = "facebook.com",
                instagram = "instagram.com",
                id = "3",
                description = "description",
                badge = "badge.png",
                banner = "banner.png",
                formedYear = 1926,
                jersey = "jersey.png",
                stadium = "Wanda Metropolitano",
                website = "atletico.com"
            ),
        )
    )

    private val successfulResponseWithNullData = BaseTeamResponse(teams = null)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockSportsAPI)
    }

    @Test
    fun `test teams are retrieved successfully from service`() {
        runBlocking {
            `when`(mockSportsAPI.getTeamsByLeague("la_liga")).thenReturn(Response.success(successfulResponse))

            val result = teamRemoteDataSource.getTeamsByLeagueName("la_liga")
            verify(mockSportsAPI).getTeamsByLeague("la_liga")

            assertEquals(Result.Success(successfulResponse.teams!!), result)
        }
    }

    @Test
    fun `test teams are retrieved successfully but teams list is null`() {
        runBlocking {
            `when`(mockSportsAPI.getTeamsByLeague("la_liga")).thenReturn(Response.success(successfulResponseWithNullData))

            val result = teamRemoteDataSource.getTeamsByLeagueName("la_liga")
            verify(mockSportsAPI).getTeamsByLeague("la_liga")

            assertEquals(true, result is Result.Failure)
        }
    }

    @Test
    fun `test teams are retrieved with error from service`() {
        runBlocking {
            `when`(mockSportsAPI.getTeamsByLeague("la_liga")).thenReturn(Response.error(500, successfulResponse.toString().toResponseBody()))

            val result = teamRemoteDataSource.getTeamsByLeagueName("la_liga")
            verify(mockSportsAPI).getTeamsByLeague("la_liga")

            assertEquals(true, result is Result.Failure)
        }
    }
}