package com.thernat.starter.repository

import com.thernat.starter.api.ApiService
import com.thernat.starter.db.BasicDao
import com.thernat.starter.exceptions.BasicException
import com.thernat.starter.vo.BasicElement
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Singleton
class MVVMRepository @Inject constructor(private val apiService: ApiService,private val basicDao: BasicDao) {

    private var cachedBasicElement: BasicElement? = null

    suspend fun getAllBasicVOAsync(): BasicElement {
        cachedBasicElement?.let {
            Timber.i("Using cached element")
            return it
        }
        if (cachedBasicElement != null){
        }
        return useDataFromDatabase()?:useDataFromRemote()
    }

    private fun useDataFromDatabase(): BasicElement? {
        Timber.i("Trying to use element saved in database")
        return basicDao.getElementWithBiggestId().also {
            cachedBasicElement = it
        }

    }

    private suspend fun useDataFromRemote(): BasicElement {
        Timber.i("Trying to use data from remote")
        try {
            val response = apiService.getBasicElementAsync()
            if(response.isSuccessful){
                response.body()?.let {basicElement ->
                    cachedBasicElement = basicElement
                    basicDao.insert(basicElement)
                    return basicElement
                }
            }
        } catch (e: Exception) {
        }
        //TODO more advanced error handling
        throw BasicException()
    }
}