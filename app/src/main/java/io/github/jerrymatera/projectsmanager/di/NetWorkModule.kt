package io.github.jerrymatera.projectsmanager.di

import io.github.jerrymatera.projectsmanager.data.network.ResponseHandler
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            expectSuccess = true

            defaultRequest {
                url("https://mngmtapp.malakoff.co/api/v1/")
                header(HttpHeaders.ContentType, "application/json")
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
    single { ResponseHandler(get()) }
}