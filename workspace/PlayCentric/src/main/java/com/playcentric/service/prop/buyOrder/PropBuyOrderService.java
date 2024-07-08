//package com.playcentric.service.prop.buyOrder;
//
//import java.time.LocalDateTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.playcentric.model.member.Member;
//import com.playcentric.model.prop.buyOrder.PropBuyOrder;
//import com.playcentric.model.prop.buyOrder.PropBuyOrderId;
//import com.playcentric.model.prop.buyOrder.PropBuyOrderRepository;
//import com.playcentric.model.prop.sellOrder.PropSellOrder;
//import com.playcentric.model.prop.sellOrder.PropSellOrderRepository;
//import com.playcentric.service.prop.sellOrder.PropSellOrderService;
//
//@Service
//public class PropBuyOrderService {
//
//    @Autowired
//    private PropSellOrderRepository propSellOrderRepo;
//
//    @Autowired
//    private PropBuyOrderRepository propBuyOrderRepo;
//
//    @Autowired
//    private PropSellOrderService propSellOrderService;
//
//    public void buyProp(int propSellOrderId, int buyQuantity, Member member, LocalDateTime orderTime, int price) {
//        // 获取销售订单
//        PropSellOrder propSellOrder = propSellOrderRepo.findById(propSellOrderId)
//            .orElseThrow(() -> new RuntimeException("Sell order not found"));
//
//        // 创建购买订单
//        PropBuyOrder propBuyOrder = new PropBuyOrder();
//        propBuyOrder.setId(new PropBuyOrderId(propSellOrderId, member.getMemId())); // 设置复合主键
//        propBuyOrder.setMember(member);
//        propBuyOrder.setOrderTime(LocalDateTime.now()); // 设置当前时间
//        propBuyOrder.setQuantity(buyQuantity);
//        propBuyOrder.setPrice(price);
//        propBuyOrder.setPropSellOrder(propSellOrder);
//        // 设置支付信息，假设有支付信息设置方法
//        // propBuyOrder.setPayment(payment);
//
//        // 保存购买订单
//        propBuyOrderRepo.save(propBuyOrder);
//
//        // 调用卖单service修改卖单的数量
//        propSellOrderService.updateSellOrderQuantity(propSellOrderId, buyQuantity);
//   
//        //調用倉庫service修改倉庫的數量
//        
//    }
//}
