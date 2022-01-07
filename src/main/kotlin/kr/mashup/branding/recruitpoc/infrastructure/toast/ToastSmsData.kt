package kr.mashup.branding.recruitpoc.infrastructure.toast

data class ToastResponse<T>(
    val header: ToastResponseHeader,
    val body: ToastResponseBody<T>,
)

data class ToastResponseBody<T>(
    val data: T,
)

data class ToastResponseHeader(
    val isSuccessful: Boolean,
    val resultCode: Long,
    val resultMessage: String,
)

data class ToastSmsResponse(
    val requestId: String,
    val statusCode: String,
    val senderGroupingKey: String?,
    val sendResultList: List<ToastSmsSendResult>,
)

data class ToastSmsSendResult(
    val recipientNo: String,
    val resultCode: Long?,
    val resultMessage: String?,
    val recipientSeq: Long?,
    val recipientGroupingKey: String?,
)

data class SmsRequest(
    // 발송 템플릿 ID
    val templateId: String? = null,
    // 표준: 90바이트, 최대: 255자(EUC-KR 기준)
    val body: String,
    // 발신 번호
    val sendNo: String,
    // 예약 일시(yyyy-MM-dd HH:mm)
    val requestDate: String? = null,
    // 발신자 그룹키
    val senderGroupingKey: String? = null,
    // 수신자 목록 (최대 1000명)
    val recipientList: List<SmsRecipient>,
    // 발송 구분자 ex)admin,system
    val userId: String? = null,
    // 통계 ID (발신 검색 조건에는 포함되지 않습니다)
    val statsId: String? = null,
)

data class SmsRecipient(
    // 수신 번호. countryCode와 조합하여 사용 가능
    val recipientNo: String,
    // 국가 번호 [기본값: 82(한국)]
    val countryCode: String? = null,
    // 국가 번호가 포함된 수신 번호. recipientNo가 있을 경우 이 값은 무시된다.
    // 예)821012345678
    val internationalRecipientNo: String? = null,
    // 템플릿 파라미터(템플릿 ID 입력 시)
    // 치환 키(##key##),
    // 치환 키에 매핑되는 Value 값
    val templateParameter: Map<String, Any>? = null,
    // 수신자 그룹키
    val recipientGroupingKey: String? = null,
)