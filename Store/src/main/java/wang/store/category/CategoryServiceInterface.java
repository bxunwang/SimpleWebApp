package wang.store.category;

import java.util.List;

public interface CategoryServiceInterface {
	
	/**
	 * 找出同階層的商品目錄
	 * @param parentId 目錄的父級id
	 * @return 同階層的商品目錄
	 */
	List<Category> findByParentId(Integer parentId);
	
}