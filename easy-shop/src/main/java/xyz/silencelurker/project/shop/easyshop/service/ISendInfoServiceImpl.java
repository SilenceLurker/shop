package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.SendInfo;
import xyz.silencelurker.project.shop.easyshop.repository.SendInfoRepository;

/**
 * @author Silence_Lurker
 */
@Log4j2
@Service
public class ISendInfoServiceImpl implements ISendInfoService {
    @Resource
    private SendInfoRepository sendInfoRepository;

    @Override
    public boolean createNewSendInfo(SendInfo newInfo) {

        if (newInfo == null) {
            return false;
        }

        log.info(newInfo);

        try {
            sendInfoRepository.save(newInfo);

            log.info("save success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public List<SendInfo> findAllByUserId(int accountId) {
        var sendInfo = new SendInfo();
        sendInfo.setAccountId(accountId + "");
        Example<SendInfo> example = Example.of(sendInfo,
                ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("defaultSelected"));

        return sendInfoRepository.findAll(example);
    }

    @Override
    public SendInfo deleteSendInfoById(@NonNull String id) {
        var info = sendInfoRepository.findById(id);

        try {
            sendInfoRepository.delete(info.get());
        } catch (Exception e) {
            return null;
        }

        return info.get();
    }

}
