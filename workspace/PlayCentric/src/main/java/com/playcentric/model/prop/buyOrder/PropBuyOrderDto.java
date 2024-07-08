package com.playcentric.model.prop.buyOrder;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playcentric.model.game.transaction.Payment;
import com.playcentric.model.game.transaction.PaymentDao;
import com.playcentric.model.member.Member;
import com.playcentric.model.member.MemberDto;
import com.playcentric.model.prop.sellOrder.PropSellOrder;
import com.playcentric.model.prop.sellOrder.PropSellOrderDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PropBuyOrderDto {
    private int orderId;
    private int buyerMemId;
    private int quantity;
    private LocalDateTime orderTime;
    private PaymentDao payment;
    private int price;
    private PropSellOrderDto propSellOrder;
    private MemberDto member;

    public PropBuyOrderDto(PropBuyOrder propBuyOrder) {
        this.orderId = propBuyOrder.getId().getOrderId();
        this.buyerMemId = propBuyOrder.getId().getBuyerMemId();
        this.quantity = propBuyOrder.getQuantity();
        this.orderTime = propBuyOrder.getOrderTime();
        this.payment = new PaymentDao(propBuyOrder.getPayment());
        this.price = propBuyOrder.getPrice();
        this.propSellOrder = new PropSellOrderDto(propBuyOrder.getPropSellOrder());
        this.member = new MemberDto(propBuyOrder.getMember());
    }
}
