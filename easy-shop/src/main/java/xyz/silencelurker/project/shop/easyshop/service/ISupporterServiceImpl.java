package xyz.silencelurker.project.shop.easyshop.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Supporter;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterInfoRepository;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterRepository;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

/**
 * @author Silence_Lurker
 */


 @Log4j2
@Service
public class ISupporterServiceImpl implements ISupporterService {

    @Resource
    private SupporterRepository supporterRepository;
    @Resource
    private SupporterInfoRepository supporterInfoRepository;

    @Override
    public void enableSupporterAccount(Supporter supporter) {
        if (supporter == null) {
            return;
        }

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
        var email = map.get("email");
        var supporter = new Supporter();
        supporter.setEmail(email);
        var example = Example.of(supporter, ExampleMatcher.matching().withIgnoreNullValues().withMatcher("email", GenericPropertyMatchers.caseSensitive()).withIgnorePaths("accountId"));

        if (supporterRepository.findOne(example).isEmpty()) {
            return null;
        }

        return supporterRepository.findAll(example).get(0);

    }

}
