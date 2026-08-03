package com.visualstudioex3.application

import com.visualstudioex3.apdater.inedb.repositories.IneAutonomousCommunityRepositoryImplementation
import com.visualstudioex3.apdater.inedb.repositories.IneMunicipalityRepository
import com.visualstudioex3.apdater.inedb.repositories.IneProvinceRepositoryImplementation
import com.visualstudioex3.application.models.MunicipalityData
import javax.inject.Inject

class MunicipalityFinderImplementation @Inject constructor(
    val ineMunicipalityRepository: IneMunicipalityRepository,
    val ineProvinceRepository: IneProvinceRepositoryImplementation,
    val ineAutonomousCommunityRepository: IneAutonomousCommunityRepositoryImplementation
) : MunicipalityFinder {
    override fun findMunicipalities(name: String): List<MunicipalityData> =
        ineMunicipalityRepository.findByName(name)
            .map {
                MunicipalityData(
                    code = it.provinceCode + it.municipalityCode,
                    name = it.name,
                    province = ineProvinceRepository.getName(it.provinceCode),
                    autnomousCommunity = ineAutonomousCommunityRepository.getName(it.autnomousCommunityCode)
                )
            }
}
