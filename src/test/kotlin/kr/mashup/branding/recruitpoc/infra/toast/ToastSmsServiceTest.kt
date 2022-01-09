package kr.mashup.branding.recruitpoc.infra.toast

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime

internal class ToastSmsServiceTest {
    private val sut = ToastSmsService(RestTemplate())


    @BeforeEach
    fun setUp() {
        ReflectionTestUtils.setField(sut, "url", "https://api-sms.cloud.toast.com/")
        ReflectionTestUtils.setField(sut, "appKey", System.getenv("TOAST_SMS_APP-KEY"))
        ReflectionTestUtils.setField(sut, "secretKey", System.getenv("TOAST_SMS_SECRET-KEY"))
    }

    @Disabled
    @Test
    fun sendSms() {
        sut.sendSms(
            sender = "01031280428",
            recipients = listOf(
                "01031280428",
            ),
            message = "test message at ${LocalDateTime.now()}",
        )
    }
}