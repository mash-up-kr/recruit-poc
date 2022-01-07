package kr.mashup.branding.recruitpoc.infrastructure.toast

import kr.mashup.branding.recruitpoc.domain.FailedToSendMessageException
import kr.mashup.branding.recruitpoc.domain.SmsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

/**
 * 발송 번호 등록 필요
 */
@Service
class ToastSmsService(
    private val toastSmsRestTemplate: RestTemplate,
) : SmsService {
    @Value("toast.sms.url")
    lateinit var url: String

    @Value("toast.sms.app-key")
    lateinit var appKey: String

    @Value("toast.sms.secret-key")
    lateinit var secretKey: String

    /**
     * 90바이트(한글 45자, 영문 90자)
     */
    override fun sendSms(sender: String, recipients: List<String>, message: String) {
        val request = SmsRequest(
            sendNo = sender,
            body = message,
            recipientList = recipients.map { SmsRecipient(recipientNo = it) },
        )
        val response = toastSmsRestTemplate.exchange(
            "$url/sms/v3.0/appKeys/$appKey/sender/sms",
            HttpMethod.POST,
            HttpEntity(request, LinkedMultiValueMap(
                mapOf(
                    "X-Secret-Key" to listOf(secretKey),
                    "Content-Type" to listOf("application/json;charset=utf-8")
                )
            )),
            object: ParameterizedTypeReference<ToastResponse<ToastSmsResponse>> () {},
        )
        if (!response.statusCode.is2xxSuccessful || !response.body!!.header.isSuccessful) {
            print(response)
            throw FailedToSendMessageException()
        }
        print("${response.body!!.body.data}")
    }

    /**
     * 제목 : 40바이트(한글 20자, 영문 40자)
     * 본문 : 2,000바이트(한글 1,000자, 영문 2,000자)
     */
    override fun sendMms(sender: String, recipients: List<String>, message: String) {
        TODO("Not yet implemented")
    }
}