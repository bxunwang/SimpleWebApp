package wang.store.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.store.common.ResponseResult;
import wang.store.order.orderinformation.OrderInformation;
import wang.store.order.orderproduct.OrderProduct;

@Controller("orderController")
public class OrderController {
	
	@Resource(name = "orderServiceImplement")
	private OrderServiceInterface orderService;
	
	/**
	 * 顯示我的訂單頁面
	 * @return 我的訂單頁面
	 */
	@RequestMapping("/orderPage.do")
	public String orderPage() {
		return "order";
	}
	
	/**
	 * 顯示訂單內容頁面
	 * @return 訂單內容頁面
	 */
	@RequestMapping("/orderDetailPage.do")
	public String orderDetailPage() {
		return "orderDetail";
	}
	
	/**
	 * 創建訂單。同時寫入orderInformation與orderProduct
	 * @param total 訂單金額
	 * @param recipientName 收件人姓名
	 * @param recipientPhone 收件人電話
	 * @param recipientAddress 收件人地址
	 * @param productId 商品id
	 * @param productNumber 商品數量
	 * @param session 會員id儲存位置
	 * @return 成功返回1，失敗返回0
	 */
	@RequestMapping("/orderAdd.do")
	@ResponseBody
	public ResponseResult<Void> orderAdd(Integer total, String recipientName, String recipientPhone, String recipientAddress, 
			Integer[] productId, Integer[] productNumber, HttpSession session) {
		
		if ("".equals(recipientName) || "".equals(recipientPhone) || "".equals(recipientAddress)) {
			return new ResponseResult<Void>(0, "請先設置收件人");
		}
		if (total == null || productId.length == 0 || productNumber.length == 0) {
			return new ResponseResult<Void>(0, "資料有誤");
		}
		Integer userId = (Integer) session.getAttribute("userId");
		Integer result = orderService.add(total, recipientName, recipientPhone, recipientAddress, productId, productNumber, userId);
		if (result == 1) {
			return new ResponseResult<Void>(1, "訂單成立");
		}
		return new ResponseResult<Void>(0, "訂單出錯");
	}
	
	/**
	 * 查詢會員的所有訂單
	 * @param session 會員id儲存位置
	 * @return 訂單列表
	 */
	@RequestMapping("/orderList.do")
	@ResponseBody
	public ResponseResult<List<OrderInformation>> orderList(HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<OrderInformation> orderInformations = orderService.orderInformationsFindByUserId(userId);
		if (orderInformations.size() > 0) {
			return new ResponseResult<List<OrderInformation>>(1, orderInformations);
		}
		return new ResponseResult<List<OrderInformation>>(0, "尚無任何訂單");
	}
	
	/**
	 * 查詢某筆訂單
	 * @param id 訂單id
	 * @param session 會員id儲存位置
	 * @return 該訂單
	 */
	@RequestMapping("/orderLoad.do")
	@ResponseBody
	public ResponseResult<OrderInformation> orderLoad(Integer id, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		OrderInformation orderInformation = orderService.orderInformationFindByUserIdAndId(userId, id);
		return new ResponseResult<OrderInformation>(1, orderInformation);
	}
	
	/**
	 * 以訂單id查詢訂單的商品資料
	 * @param orderId 訂單id
	 * @return 訂單的商品資料
	 */
	@RequestMapping("/orderProductLoad.do")
	@ResponseBody
	public ResponseResult<List<OrderProduct>> orderProductLoad(Integer orderId) {
		List<OrderProduct> orderProducts = orderService.orderProductFindById(orderId);
		return new ResponseResult<List<OrderProduct>>(1, orderProducts);
	}
	
	/**
	 * 取消訂單，只有待付款、待出貨的訂單能取消
	 * @param id 訂單id
	 * @param session 會員id儲存位置
	 * @return 成功返回1，失敗返回0
	 */
	@RequestMapping("/orderCancel.do")
	@ResponseBody
	public ResponseResult<Void> orderCancel(Integer id, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		OrderInformation orderInformation = orderService.orderInformationFindByUserIdAndId(userId, id);
		if (orderInformation.getState() < 2) {
			orderService.orderStateChange(id, userId, 4);
			return new ResponseResult<Void>(1, "已取消");
		}
		return new ResponseResult<Void>(0, "無法取消");
	}
}
