package org.example.forecast.requesthandlers

/**
 * Contract for forecast request handlers.
 *
 * Request handlers implements the specific logic to make the server request and receive the server response data.
 *
 * @param TRequest Type of the request model.
 * @param TResponse Type of the response model.
 */
interface IForecastRequestHandler<TRequest, TResponse> {
    /**
     * Invokes the HTTP request.
     *
     * @param request [TRequest] request object.
     *
     * @return Returns the [TResponse] response object.
     */
    suspend fun invoke(request: TRequest): TResponse
}