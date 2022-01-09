package kr.mashup.branding.recruitpoc.domain.application.form

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface QuestionService {
    fun createQuestion(createQuestionVo: CreateQuestionVo): Question
}

@Service
class QuestionServiceImpl(
    private val questionRepository: QuestionRepository,
) : QuestionService {
    @Transactional
    override fun createQuestion(createQuestionVo: CreateQuestionVo): Question {
        return questionRepository.save(Question(createQuestionVo))
    }
}