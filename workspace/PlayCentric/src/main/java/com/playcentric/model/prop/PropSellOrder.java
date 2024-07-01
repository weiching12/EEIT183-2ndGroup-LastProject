package com.playcentric.model.prop;
import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="PropSellOrder")
public class PropSellOrder {
//	PK
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
    @EmbeddedId
    private MemberPropInventoryId memberPropInventoryId;
    
//  FK倉庫的複合PK 賣家會員編號->倉庫(會員編號)，道具編號->倉庫(道具編號)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberPropInventoryId")
    @JoinColumns({
        @JoinColumn(name = "sellerMemrId", referencedColumnName = "memId"),
        @JoinColumn(name = "propId", referencedColumnName = "propId")
    })
    private MemberPropInventory memberPropInventory; 
    
    private int amount;
    private int quantity;
    private Date saleTime;
    private Date expiryTime;
    private byte orderStatus;
}   
