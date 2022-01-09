package kr.mashup.branding.recruitpoc.domain.team

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TeamService {
    fun createTeam(createTeamVo: CreateTeamVo): Team
    fun getTeam(teamId: Long): Team
    fun findAllTeams(): List<Team>
}

@Service
@Transactional(readOnly = true)
class TeamServiceImpl(
    private val teamRepository: TeamRepository,
) : TeamService {
    @Transactional
    override fun createTeam(createTeamVo: CreateTeamVo): Team {
        return teamRepository.save(Team(createTeamVo = createTeamVo))
    }

    override fun getTeam(teamId: Long): Team {
        return teamRepository.findByIdOrNull(teamId)
            ?: throw TeamNotFoundException()
    }

    override fun findAllTeams(): List<Team> {
        return teamRepository.findAll()
    }
}