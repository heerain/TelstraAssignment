package com.example.telstraassignment.network

import com.example.telstraassignment.model.FactsResponseMain
import com.example.telstraassignment.util.ENDPOINT_URL
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface FactsApi {
    /**
     * Get the list of the facts from the API
     */
    @GET(ENDPOINT_URL)
    fun getFacts(): Observable<FactsResponseMain>
}