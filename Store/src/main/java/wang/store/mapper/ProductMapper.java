package wang.store.mapper;

import java.util.List;

import wang.store.bean.Product_category;

public interface ProductMapper {
	
	/**
	 * 找出同階層的商品目錄
	 * @param parentId 目錄的父級id
	 * @return 同階層的商品目錄
	 */
	List<Product_category> findCategoryByParentId(Integer parentId);
}
