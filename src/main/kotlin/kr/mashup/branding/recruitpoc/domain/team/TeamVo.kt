package kr.mashup.branding.recruitpoc.domain.team

import java.time.LocalDateTime

data class TeamVo(
    val teamId: Long,
    val name: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    constructor(team: Team) : this(
        teamId = team.teamId,
        name = team.name,
        description = team.description,
        createdAt = team.createdAt,
        updatedAt = team.updatedAt,
    )
}