package kr.mashup.branding.recruitpoc.domain.member

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface MemberService {
    fun getOrCreate(kakaoMemberId: String): Member
    fun getMember(memberId: Long): Member
}

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
) : MemberService {
    @Transactional
    override fun getOrCreate(kakaoMemberId: String): Member {
        val member = memberRepository.findByKakaoMemberId(kakaoMemberId)
        if (member != null) {
            return member
        }
        return memberRepository.save(Member(
            name = "",
            kakaoMemberId = kakaoMemberId,
        ))
    }

    override fun getMember(memberId: Long): Member {
        return memberRepository.findByIdOrNull(memberId) ?: throw MemberNotFoundException()
    }
}