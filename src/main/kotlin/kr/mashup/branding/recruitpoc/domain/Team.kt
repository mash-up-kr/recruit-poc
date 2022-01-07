package kr.mashup.branding.recruitpoc.domain

class Team(
    val id: Long,
    val name: String,
    val description: String,
) {
    companion object {
        private val teams = listOf(
            Team(id = 1L, name = "디자인", description = "Design"),
            Team(id = 2L, name = "웹", description = "Web"),
            Team(id = 3L, name = "안드", description = "Android"),
            Team(id = 4L, name = "아오스", description = "iOS"),
            Team(id = 5L, name = "노드", description = "Node"),
            Team(id = 6L, name = "스프링", description = "Spring"),
        )

        fun getTeams(): List<Team> = teams
    }
}