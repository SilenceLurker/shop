package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;

import xyz.silencelurker.project.shop.easyshop.entity.MemoryAndDisk;

/**
 * @author Silence_Lurker
 */
public interface IMemoryAndDiskService {
    /**
     * get all memory and disk info
     * 
     * @return
     */
    List<MemoryAndDisk> findAllMemoryAndDisks();

    /**
     * get all matched memory and disk info
     * 
     * @param example
     * 
     * @return
     */
    List<MemoryAndDisk> findMatchMemoryAndDisks(Example<MemoryAndDisk> example);

    /**
     * add new Mem
     * 
     * @param memoryAndDisk
     */
    void addMemoryAndDisk(MemoryAndDisk memoryAndDisk);
}
