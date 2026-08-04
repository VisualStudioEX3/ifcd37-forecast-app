package com.visualstudioex3.application

/**
 * Contract for use cases.
 *
 * Use cases implements the specific logic to transform the server response data to client response
 * data.
 *
 * @param TRequest Type of the request model.
 * @param TResponse Type of the response model.
 */
interface UseCase<TRequest, TResponse> {
    /**
     * Invokes the use case.
     *
     * @param request [TRequest] request object.
     *
     * @return Returns the [TResponse] response object.
     */
    suspend fun invoke(request: TRequest): TResponse
}
