package kr.mashup.branding.recruitpoc.domain.team

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Team(
    @Id
//    @GeneratedValue
    val teamId: Long = 0L,
    val name: String,
    val description: String,
) {
    companion object {
        private val teams = listOf(
            Team(teamId = 1L, name = "디자인", description = "Design"),
            Team(teamId = 2L, name = "웹", description = "Web"),
            Team(teamId = 3L, name = "안드", description = "Android"),
            Team(teamId = 4L, name = "아오스", description = "iOS"),
            Team(teamId = 5L, name = "노드", description = "Node"),
            Team(teamId = 6L, name = "스프링", description = "Spring"),
        )

        fun getTeams(): List<Team> = teams
    }
}