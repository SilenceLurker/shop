package xyz.silencelurker.project.shop.easyshop.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

/**
 * @author Silence_Lurker
 */
@Log4j2
@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    private static final char[] NUMBER_CHARACTER_SET = "0123456789".toCharArray();

    /**
     * 向指定邮箱发送一个六位随机数字验证码
     * 
     * @param target
     * @return 将发送的六位随机数字验证码一并返回
     */
    public String confirmEmailSend(String target) {
        var mimeMessage = sender.createMimeMessage();
        MimeMailMessage mailMessage = new MimeMailMessage(mimeMessage);

        String confirmCode = randomConfirmCode();

        try {
            mailMessage.setFrom(from);
            mailMessage.setTo(target);
            mailMessage.setSubject("Confirm Code");
            mailMessage.setText("Your Confirm Code is :" + confirmCode);

            sender.send(mimeMessage);
        } catch (Exception e) {
            log.error("Mail Send Fail!");
        }

        return confirmCode;
    }

    private static final int BASE_CONFIRM_CODE_LENGTH = 5;

    protected String randomConfirmCode() {
        return randomConfirmCode(BASE_CONFIRM_CODE_LENGTH);
    }

    protected String randomConfirmCode(int length) {
        Random random = new Random(System.currentTimeMillis());
        StringBuffer confirmCode = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(NUMBER_CHARACTER_SET.length);
            char c = NUMBER_CHARACTER_SET[(num == 0 && i == 0) ? 1 : num];

            confirmCode.append(c);

        }

        return confirmCode.toString();
    }

}
