package xyz.silencelurker.project.shop.easyshop.utils;

import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.MemoryAndDisk;

/**
 * @author Silence_Lurker
 */
@Log4j2
public class MemoryAndDiskUtil {
    private MemoryAndDiskUtil() {
    }

    public static MemoryAndDisk buildMemoryAndDisk(int id) {
        var memoryAndDisk = new MemoryAndDisk();

        memoryAndDisk.setDisk((short) ((short)id & MemoryAndDisk.OTHER_DISK));
        memoryAndDisk.setMemory((short) (((short)id & MemoryAndDisk.OTHER_MENORY) >> MemoryAndDisk.MEMORY_MOVE));
        memoryAndDisk.setId((short) id);
        memoryAndDisk.setName(memoryAndDisk.toString());

        log.info(memoryAndDisk);

        return memoryAndDisk;
    }

}
