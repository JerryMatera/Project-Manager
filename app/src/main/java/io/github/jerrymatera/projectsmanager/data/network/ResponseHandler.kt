package io.github.jerrymatera.projectsmanager.data.network

import io.github.jerrymatera.projectsmanager.data.utils.NetworkException
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResponseHandler(val httpClient: HttpClient) {
    suspend inline fun <reified B, reified R> executeRequest(
        method: HttpMethod,
        urlPathSegments: List<Any>,
        body: B? = null,
        queryParams: Map<String, Any>? = null,
    ): NetworkResult<R> {
        return withContext(Dispatchers.IO) {
            try {
                val response = httpClient.prepareRequest {
                    this.method = method
                    url {
                        val pathSegments = urlPathSegments.map { it.toString() }
                        appendPathSegments(pathSegments)
                    }
                    body?.let { setBody(body) }
                    queryParams?.let { params ->
                        params.forEach { (key, value) ->
                            parameter(key, value)
                        }
                    }
                }.execute().body<R>()
                NetworkResult.Success(response)
            } catch (e: Exception) {
                if (e is ResponseException) {
                    val errorBody = e.response.body<R>()
                    when (e.response.status) {
                        HttpStatusCode.Unauthorized -> {
                            NetworkResult.Error(
                                body = errorBody,
                                exception = NetworkException.UnauthorizedException(
                                    "Unauthorized",
                                    e,
                                )
                            )
                        }

                        else -> {
                            NetworkResult.Error(
                                body = errorBody,
                                exception = NetworkException.NotFoundException("API Not found", e)
                            )
                        }
                    }
                } else {
                    NetworkResult.Error(
                        null,
                        NetworkException.UnknownException(e.message ?: "Unknown error", e)
                    )
                }
            }
        }
    }


    suspend inline fun <reified R> get(
        urlPathSegments: List<Any>,
        queryParams: Map<String, Any>? = null,
    ): NetworkResult<R> = executeRequest<Any, R>(
        method = HttpMethod.Get,
        urlPathSegments = urlPathSegments.toList(),
        queryParams = queryParams,
    )

    suspend inline fun <reified B, reified R> post(
        urlPathSegments: List<Any>,
        body: B? = null,
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Post,
        urlPathSegments = urlPathSegments.toList(),
        body = body,
    )

    suspend inline fun <reified B, reified R> put(
        urlPathSegments: List<Any>,
        body: B? = null,
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Put,
        urlPathSegments = urlPathSegments.toList(),
        body = body,
    )

    suspend inline fun <reified R> put(
        urlPathSegments: List<Any>,
    ): NetworkResult<R> = executeRequest<Any?, R>(
        method = HttpMethod.Put,
        urlPathSegments = urlPathSegments.toList(),
    )

    suspend inline fun <reified R> delete(
        urlPathSegments: List<Any>,
    ): NetworkResult<R> = executeRequest<Any?, R>(
        method = HttpMethod.Delete,
        urlPathSegments = urlPathSegments.toList(),
    )
}