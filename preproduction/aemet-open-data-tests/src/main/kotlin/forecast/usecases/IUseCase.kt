package org.example.forecast.usecases

/**
 * Contract for forecast use case.
 *
 * @param TRequest Type of the request model.
 * @param TResponse Type of the response model.
 */
interface IUseCase<TRequest, TResponse> {
    /**
     * Invokes the use case.
     *
     * @param request [TRequest] request object.
     *
     * @return Returns the [TResponse] response object.
     */
    suspend fun invoke(request: TRequest): TResponse
}