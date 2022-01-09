package kr.mashup.branding.recruitpoc.domain.admin.staff

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface StaffService {
    fun getOrCreate(kakaoMemberId: String): Staff
}

@Service
@Transactional(readOnly = true)
class StaffServiceImpl(
    private val staffRepository: StaffRepository,
): StaffService {
    @Transactional
    override fun getOrCreate(kakaoMemberId: String): Staff {
        val staff = staffRepository.findByKakaoMemberId(kakaoMemberId)
        if (staff != null) {
            return staff
        }
        return staffRepository.save(Staff(kakaoMemberId = kakaoMemberId))
    }

}