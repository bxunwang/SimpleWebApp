package wang.store.user;

public interface UserMapper {
	/**
	 * 向資料庫新增會員
	 * @param user 新會員的資料
	 * @return 受影響的行數
	 */
	Integer insert(User user);
	
	/**
	 * 根據會員的id查找會員資料
	 * @param userId 會員id
	 * @return 未被刪除的會員資料
	 */
	User selectByUserId(Integer userId);
	
	/**
	 * 根據會員帳號名稱查找會員資料
	 * @param username 會員帳號名稱
	 * @return 未被刪除的會員資料
	 */
	User selectByUsername(String username);
	
	/**
	 * 修改會員的資料
	 * @param user 新的會員資料
	 * @return 受影響的行數
	 */
	Integer update(User user);
}
