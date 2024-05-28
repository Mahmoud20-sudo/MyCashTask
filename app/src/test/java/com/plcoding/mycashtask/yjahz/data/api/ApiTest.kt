package com.plcoding.mycashtask.yjahz.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.data.model.user.UserModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SmallTest
class ApiTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var server: MockWebServer

    private lateinit var api: Api

    @Before
    fun setup() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))//Pass any base url like this
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }

    @After
    fun tea() {
        server.shutdown()
    }

    @Test
    fun `login with valid credentials, returns Success`() = runTest {
        val dto = UserModel()//The object I want back as response
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)!!//Convert the object into json string using GSON
        val res = MockResponse()//Make a fake response for our server call
        res.setBody(json)//set the body of the fake response as the json string you are expecting as a response
        server.enqueue(res)//add it in the server response queue

        val data = api.login("mahmoud08@live.com", "12345678")//make the call to our fake server(as we are using fake base url)
        server.takeRequest()//let the server take the request

        assertEquals(data.body(), dto)//the data you are getting as the call response should be same
    }

    @Test
    fun `login with invalid credentials, returns Error`() = runTest {
        //First step is to make the server ready with a response
        val res = MockResponse()
        res.setResponseCode(404)
        server.enqueue(res)

        //Second step is to create a call
        val response = api.login("ali@live.com", "55543687")//make the call to our fake server(as we are using fake base url)
        //Third step is to tell our server to accept the call created
        server.takeRequest()

        assert(!response.isSuccessful)//Our repo shows error as the response code was 400.
    }

    @Test
    fun `register with valid credentials, returns Success`() = runTest {
        val dto = UserModel()//The object I want back as response
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)!!//Convert the object into json string using GSON
        val res = MockResponse()//Make a fake response for our server call
        res.setBody(json)//set the body of the fake response as the json string you are expecting as a response
        server.enqueue(res)//add it in the server response queue

        val data = api.register(name = "Mahmoud Mohamed", email = "ali_Sameh@live.com", phone = "01142204177", password = "12345678")//make the call to our fake server(as we are using fake base url)
        server.takeRequest()//let the server take the request

        assertEquals(data.body(), dto)//the data you are getting as the call response should be same
    }

    @Test
    fun `register with existing email, returns Error`() = runTest {
        //First step is to make the server ready with a response
        val res = MockResponse()
        res.setResponseCode(404)
        server.enqueue(res)
        //Second step is to create a call
        val response = api.register(name = "Mahmoud Mohamed", email = "mahmoud08@live.com", phone = "01142204177", password = "12345678")//make the call to our fake server(as we are using fake base url)
        //Third step is to tell our server to accept the call created
        server.takeRequest()//let the server take the request

        assert(!response.isSuccessful)//Our repo shows error as the response code was 400.
    }

    @Test
    fun `request popular sellers, returns Success`() = runTest {
        val dto = PopularSeller()//The object I want back as response
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)!!//Convert the object into json string using GSON
        val res = MockResponse()//Make a fake response for our server call
        res.setBody(json)//set the body of the fake response as the json string you are expecting as a response
        server.enqueue(res)//add it in the server response queue

        val response = api.popularNow(lat = 29.1931, lng = 30.6421, filter = 1, )//make the call to our fake server(as we are using fake base url)
        server.takeRequest()//let the server take the request

        assert(response.isSuccessful)//Our repo shows error as the response code was 400.
    }

    @Test
    fun `request trending sellers, returns Success`() = runTest {
        val dto = PopularSeller()//The object I want back as response
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)!!//Convert the object into json string using GSON
        val res = MockResponse()//Make a fake response for our server call
        res.setBody(json)//set the body of the fake response as the json string you are expecting as a response
        server.enqueue(res)//add it in the server response queue

        val response = api.trending(lat = 29.1931, lng = 30.6421, filter = 1, )//make the call to our fake server(as we are using fake base url)
        server.takeRequest()//let the server take the request

        assert(response.isSuccessful)//Our repo shows error as the response code was 400.
    }
}