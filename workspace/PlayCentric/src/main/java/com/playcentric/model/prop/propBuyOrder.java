package com.playcentric.model.prop;

import java.util.Date;

import com.playcentric.model.member.Member;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "propBuyOrder")
public class propBuyOrder {
	@EmbeddedId
    private propBuyOrderId id;
	
//	FK 委託賣單
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    private PropSellOrder propSellOrder;

//	FK 會員
    @ManyToOne
    @MapsId("menId")
    @JoinColumn(name = "menId")
    private Member member;
    
    private int quantity;
    private Date orderTime;
    
//  FK 付款
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentId", referencedColumnName = "paymentId", insertable = false, updatable = false)
    private Payment payment;
	
}
