package xyz.silencelurker.project.shop.easyshop.utils;

import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Production;

/**
 * @author Silence_Lurker
 */
@Log4j2
public class ProductionUtil {
    private ProductionUtil() {
    }

    public static Long productionIdBuild(Production production) {
        Long productionId =  0L;

        productionId = productionId | (long)production.getSubId();
        productionId = productionId | (long)(production.getEnable() ? 0b1000000000000L : 0L);
        productionId = productionId | (long)((long)(production.getType().getTypeCode()) << 13L);
        productionId = productionId | (long)((long)(production.getSystem().getId()) << 16L);
        productionId = productionId | ((long)((long)(production.getColor().getId()) & 0b11111000000000000L) << (13L));
        // 我不知道为啥我当初设计的时候中间空了五个位置……
        productionId = productionId | (long)((long)(production.getMemoryAndDisk().getId()) << 30L);
        productionId = productionId | (long)((long)(production.getBrand().getId()) << 42L);

        return productionId;
    }
}
