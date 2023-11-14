# shop项目Production类API

通过Lombok，JPA，MySQL初始化对象并设定预期方法，需在production-service包中实现service包下的所有接口并添加@Service或@DubboService进行IOC容器注入

## 相关类

- Production
- Brand
- MemoryAndPrice
- Color

## 相关方法及接口需求分析

品牌列表

相关类：Brand

参数名：brand（匹配属性：brandName）

需求方法：数据库根据名称进行模糊查询（包含）

Repository方法：

- Page<Brand> findAllByBrandNameContaining(String brandName,Pageable pageable);
- List<Brand> findAllByBrandNameContaining(String brandName);

对应Service接口需求方法：

- Page<Brand> findAllBrandByName(String brandName,Pageable);
- List<Brand> findAllBrandByName(String brandName);

商品列表（按照销量最高返回 ~~这TM不就是按销量排序~~ ）

相关类：Production，Brand

参数名：brand（匹配属性：brandName），number（需转换为Pageable）

需求方法：数据库查询所有brand的brandName属性为与参数类似的方法

可使用上一个接口中的方法来查询所有类似的Brand对象并返回id，在ProductionRepository中进行二次查找：

ProductionRepository方法：

- Page<Production> findAllByBrandIdInOrderBySales(Iterable<Integer> brandId,Pageable pageable);
- List<Production> findAllByBrandIdInOrderBySales(Iterable<Integer> brandId);

IProductionService方法：

- Page<Production> getProduceList(String brand,int nubmer);
