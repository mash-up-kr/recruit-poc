package kr.mashup.branding.recruitpoc.domain.notification

interface SmsService {
    fun sendSms(sender: String, recipients: List<String>, message: String)
    fun sendMms(sender: String, recipients: List<String>, message: String)
}