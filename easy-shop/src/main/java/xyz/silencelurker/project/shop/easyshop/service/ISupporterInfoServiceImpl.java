package xyz.silencelurker.project.shop.easyshop.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.SupporterInfo;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterInfoRepository;

/**
 * @author Silence_Lurker
 */

@Service
public class ISupporterInfoServiceImpl implements ISupporterInfoService {

    @Resource
    private SupporterInfoRepository supporterInfoRepository;

    @Override
    public void registerSupporterInfo(SupporterInfo newInfo) {

        if (newInfo == null) {
            return;
        }

        supporterInfoRepository.save(newInfo);
    }

    @Override
    public boolean checkSupporterInfo(int accountId) {
        return !supporterInfoRepository.findById(accountId).isEmpty();
    }

}
