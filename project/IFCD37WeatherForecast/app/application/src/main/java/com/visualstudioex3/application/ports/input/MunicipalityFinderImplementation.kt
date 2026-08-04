package com.visualstudioex3.application.ports.input

import com.visualstudioex3.apdater.inedb.repositories.IneAutonomousCommunityRepository
import com.visualstudioex3.apdater.inedb.repositories.IneMunicipalityRepository
import com.visualstudioex3.apdater.inedb.repositories.IneProvinceRepository
import com.visualstudioex3.application.entities.Municipality
import javax.inject.Inject

internal class MunicipalityFinderImplementation @Inject constructor(
    val ineMunicipalityRepository: IneMunicipalityRepository,
    val ineProvinceRepository: IneProvinceRepository,
    val ineAutonomousCommunityRepository: IneAutonomousCommunityRepository
) : MunicipalityFinder {
    override fun findMunicipalities(name: String): List<Municipality> =
        ineMunicipalityRepository.findByName(name)
            .map {
                Municipality(
                    code = it.provinceCode + it.municipalityCode,
                    name = it.name,
                    province = ineProvinceRepository.getName(it.provinceCode),
                    autonomousCommunity = ineAutonomousCommunityRepository.getName(it.autnomousCommunityCode)
                )
            }
}
