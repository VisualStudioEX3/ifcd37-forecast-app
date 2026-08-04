package com.visualstudioex3.apdater.aemet

/**
 * AEMET OpenData API request contract.
 *
 * @param TRequestBody Request parameters model type.
 * @param TResponse Response model type.
 */
interface AemetOpenDataRequest<TRequestBody, TResponse> {
    /**
     * Invokes the request.
     *
     * @param requestBody Request body parameters.
     *
     * @return Returns [TResponse] object.
     */
    suspend fun invoke(requestBody: TRequestBody): TResponse
}
