package com.example.sampleproject.data.repository

import com.example.sampleproject.domain.repository.NetworkRepository

class NetworkRepositoryImpl() : NetworkRepository {

    override fun getApiResponse(): String {
//        return apiInterface.getApi() Need to inject the api interface
        return "Success"
    }


}