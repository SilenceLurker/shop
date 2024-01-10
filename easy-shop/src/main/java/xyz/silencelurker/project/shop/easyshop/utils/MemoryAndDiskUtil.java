package xyz.silencelurker.project.shop.easyshop.utils;

import xyz.silencelurker.project.shop.easyshop.entity.MemoryAndDisk;

/**
 * @author Silence_Lurker
 */
public class MemoryAndDiskUtil {
    private MemoryAndDiskUtil() {
    }

    public static MemoryAndDisk buildMemoryAndDisk(int id) {
        var memoryAndDisk = new MemoryAndDisk();

        memoryAndDisk.setDisk((short) (id | MemoryAndDisk.OTHER_DISK));
        memoryAndDisk.setMemory((short) (id >> MemoryAndDisk.MEMORY_MOVE));

        return memoryAndDisk;
    }

}
