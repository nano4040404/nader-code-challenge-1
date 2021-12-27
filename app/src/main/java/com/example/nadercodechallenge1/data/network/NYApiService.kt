package com.example.nadercodechallenge1.data.network

import com.example.nadercodechallenge1.data.network.responce.MostViewedSectionResponce
import com.example.nadercodechallenge1.utils.AppConstants.URL_ADDITIONS
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path



//https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=kR3JfuNcVHzlgeKzMZGvLkzrY9Tr1bje
interface NYApiService {
    @GET(value = URL_ADDITIONS)
    fun getMostViewed(@Path("section") section: String,
                      @Path("period") period: Int): Deferred<MostViewedSectionResponce>


}