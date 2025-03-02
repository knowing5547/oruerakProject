package com.oru.oruerakproject.auth.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class authService {
    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecretKey;

    @Value("${coolsms.sender}")  // 설정 파일에서 발신번호 가져오기
    private String senderPhone;

    private DefaultMessageService messageService;
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    // SMS 전송 및 인증번호 저장
    public void sendVerificationCode(String phoneNumber) {
        String verificationCode = String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 랜덤 숫자 생성

        Message message = new Message();
        message.setFrom(senderPhone); // 발신번호 입력
        message.setTo(phoneNumber);
        message.setText("[Moyiza] 인증번호: " + verificationCode);

        this.messageService.sendOne(new SingleMessageSendingRequest(message));

        // 인증번호 저장
        verificationCodes.put(phoneNumber, verificationCode);
    }

    // 인증번호 확인
    public boolean verifyCode(String phoneNumber, String inputCode) {
        String correctCode = verificationCodes.get(phoneNumber);
        return correctCode != null && correctCode.equals(inputCode);
    }
}
