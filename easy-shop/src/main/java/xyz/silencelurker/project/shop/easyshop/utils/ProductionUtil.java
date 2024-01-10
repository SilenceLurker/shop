package xyz.silencelurker.project.shop.easyshop.utils;

import xyz.silencelurker.project.shop.easyshop.entity.Production;

/**
 * @author Silence_Lurker
 */
public class ProductionUtil {
    private ProductionUtil() {
    }

    public static Long productionIdBuild(Production production) {
        Long productionId = (long) 0;

        productionId = productionId | production.getSubId();
        productionId = productionId | (production.getEnable() ? 0b1000000000000 : 0);
        productionId = productionId | (production.getType().getTypeCode() << 13);
        productionId = productionId | (production.getSystem().getId() << 16);
        productionId = productionId | ((production.getColor().getId() & 0b11111000000000000) << 20);
        // 我不知道为啥我当初设计的时候中间空了五个位置……
        productionId = productionId | (production.getMemoryAndDisk().getId() << 30);
        productionId = productionId | (production.getBrand().getId() << 42);

        return productionId;
    }
}
