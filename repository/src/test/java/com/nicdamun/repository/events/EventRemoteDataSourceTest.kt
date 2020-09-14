package com.nicdamun.repository.events

import com.nicdamun.core.dto.BaseEventResponse
import com.nicdamun.core.dto.EventDTO
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

class EventRemoteDataSourceTest {

    private val mockSportsAPI = mock(SportsAPI::class.java)
    private val eventRemoteDataSource = EventRemoteDataSource(mockSportsAPI)
    private val successfulResponse = BaseEventResponse(
        events = listOf(
            EventDTO(
                leagueName = "La Liga",
                name = "Real Madrid vs Granada",
                dateEvent = "2020-08-10"
            ),
            EventDTO(
                leagueName = "La Liga",
                name = "Real Madrid vs Barcelona",
                dateEvent = "2020-08-15"
            ),
            EventDTO(
                leagueName = "La Liga",
                name = "Real Madrid vs Atlético Madrid",
                dateEvent = "2020-08-20"
            )
        )
    )
    private val successfulResponseWithNullData = BaseEventResponse(
        events = null
    )

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockSportsAPI)
    }

    @Test
    fun `test events are retrieved successfully from service`() {
        runBlocking {
            `when`(mockSportsAPI.getEventsByTeamId("123")).thenReturn(Response.success(successfulResponse))

            val result = eventRemoteDataSource.getEventsByTeamId("123")
            verify(mockSportsAPI).getEventsByTeamId("123")

            assertEquals(Result.Success(successfulResponse.events!!), result)
        }
    }

    @Test
    fun `test events are retrieved successfully from service when events are null`() {
        runBlocking {
            `when`(mockSportsAPI.getEventsByTeamId("123")).thenReturn(Response.success(successfulResponseWithNullData))

            val result = eventRemoteDataSource.getEventsByTeamId("123")
            verify(mockSportsAPI).getEventsByTeamId("123")

            assertEquals(true, result is Result.Failure)
        }
    }

    @Test
    fun `test events are retrieved with error from service`() {
        runBlocking {
            `when`(mockSportsAPI.getEventsByTeamId("123")).thenReturn(Response.error(500, successfulResponse.toString().toResponseBody()))

            val result = eventRemoteDataSource.getEventsByTeamId("123")
            verify(mockSportsAPI).getEventsByTeamId("123")

            assertEquals(true, result is Result.Failure)
        }
    }
}