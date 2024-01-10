package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.MemoryAndDisk;
import xyz.silencelurker.project.shop.easyshop.repository.MemoryAndDiskRepository;

/**
 * @author Silence_Lurker
 */
@Service
public class IMemoryAndDiskServiceImpl implements IMemoryAndDiskService {
    @Resource
    private MemoryAndDiskRepository repository;

    @Override
    public List<MemoryAndDisk> findAllMemoryAndDisks() {
        return repository.findAll();
    }

    @Override
    public List<MemoryAndDisk> findMatchMemoryAndDisks(Example<MemoryAndDisk> example) {
        return repository.findAll(example);
    }

}
