package org.example.forecast.hydrators

/**
 * Contract for request hydrators.
 *
 * Request hydrators implements the specific code to get and compose the required parameters for a HTTP request object.
 *
 * @param TRequest Type of the request model.
 * @param TParameters Type of the request hydrator parameters model.
 */
interface IRequestHydrator<TRequest, TParameters> {
    /**
     * Creates the [TRequest] model with the required parameters.
     *
     * @param parameters Parameters object to use with this hydrator.
     */
    fun hydrate(parameters: TParameters): TRequest
}