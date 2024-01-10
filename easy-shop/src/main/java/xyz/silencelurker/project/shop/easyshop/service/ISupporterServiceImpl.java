package xyz.silencelurker.project.shop.easyshop.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Supporter;
import xyz.silencelurker.project.shop.easyshop.entity.SupporterInfo;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterInfoRepository;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterRepository;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

/**
 * @author Silence_Lurker
 */
@Service
public class ISupporterServiceImpl implements ISupporterService {

    @Resource
    private SupporterRepository supporterRepository;
    @Resource
    private SupporterInfoRepository supporterInfoRepository;

    @Override
    public void enableSupporterAccount(Supporter supporter) {
        supporterRepository.save(supporter);
    }

    @Override
    public Supporter updateSupporterInfo(Supporter newSupporterInfo) {
        var target = new Supporter();
        target.setAccountId(newSupporterInfo.getAccountId());
        var example = Example.of(target, ExampleMatcher.matching().withIgnoreNullValues());

        target = supporterRepository.findOne(example).get();

        supporterRepository.save(newSupporterInfo);

        return target;
    }

    @Override
    public Supporter supporterLoginIn(int id, String password) {
        var target = new Supporter();
        target.setAccountId(id);
        target.setPassword(password);

        try {
            target = supporterRepository.findOne(Example.of(target, ExampleMatcher.matching().withIgnoreNullValues()))
                    .get();
        } catch (Exception e) {
            return null;
        }

        return target;

    }

    @Override
    public Supporter supporterLoginIn(String cookie) {
        var map = decodeToken(cookie);
        var targetId = map.get("id");
        var supporterInfo = new SupporterInfo();
        supporterInfo.setAccountId(Integer.parseInt(targetId));
        var example = Example.of(supporterInfo, ExampleMatcher.matching().withIgnoreNullValues());

        if (supporterInfoRepository.findOne(example).isEmpty()) {
            return null;
        }

        return supporterRepository.findById(Integer.parseInt(targetId)).get();

    }

}
