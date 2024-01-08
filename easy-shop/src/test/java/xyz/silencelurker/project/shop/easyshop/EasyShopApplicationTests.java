package xyz.silencelurker.project.shop.easyshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.MemoryAndDisk;
import xyz.silencelurker.project.shop.easyshop.repository.MemoryAndDiskRepository;

@Log4j2
@SpringBootTest
class EasyShopApplicationTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String from;

	/**
	 * 163邮箱为啥TM这么慢？不应该啊？可能是代理的问题？
	 * 
	 * @author Silence_Lurker
	 */
	@Test
	public void sendTest() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper mailMessage = new MimeMessageHelper(message);

		for (int i = 0; i < 10; i++) {
			try {
				System.err.println("Start:" + System.currentTimeMillis());
				mailMessage.setFrom(from);

				mailMessage.setSubject("Mail Sender Test!");

				mailMessage.setText("Mail Text Info Test ,helper test" + i + " !");

				mailMessage.setTo("3506704365@qq.com");

				sender.send(mailMessage.getMimeMessage());
				System.err.println("End:" + System.currentTimeMillis());
			} catch (Exception e) {
				log.error("Error! Send Mail Fail!");
			}
		}
	}

	@Resource
	private MemoryAndDiskRepository memoryAndDiskRepository;

	@Test
	public void init() {
		var length = memoryAndDiskRepository.findAll().size();

		if (length > 0) {
			return;
		}
		for (short i = Short.MIN_VALUE; i < Short.MAX_VALUE; i++) {
			MemoryAndDisk memoryAndDisk = new MemoryAndDisk();
			memoryAndDisk.setId(i);
			memoryAndDisk.setDisk((short) (i | MemoryAndDisk.OTHER_DISK));
			memoryAndDisk.setMemory((short) (i | MemoryAndDisk.OTHER_MENORY));
			memoryAndDisk.setName(
					((memoryAndDisk.getMemory() + 1) << 1) + " + " + ((memoryAndDisk.getDisk() + 1) << 4) + "GB");
			memoryAndDiskRepository.save(memoryAndDisk);
		}
	}
}
