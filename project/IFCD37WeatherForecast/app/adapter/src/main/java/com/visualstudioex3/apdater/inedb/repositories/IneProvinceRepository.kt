package com.visualstudioex3.apdater.inedb.repositories

/**
 * INE province repository service.
 *
 * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
 */
interface IneProvinceRepository {
    /**
     * Gets province name by code.
     *
     * @return Returns the name of the province.
     *
     * @throws IllegalArgumentException Throws if [code] is empty or blank string.
     * @throws NoSuchElementException Throws if [code] not found.
     */
    fun getName(code: String): String
}
