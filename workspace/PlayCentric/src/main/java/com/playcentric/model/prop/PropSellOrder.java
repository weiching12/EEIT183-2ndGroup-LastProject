package com.playcentric.model.prop;

import java.util.Date;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "PropSellOrder")
public class PropSellOrder {

    @EmbeddedId
    private MemberPropInventoryId memberPropInventoryId;

    // FK 倉庫的複合 PK 賣家會員編號 -> 倉庫 (會員編號)，道具編號 -> 倉庫 (道具編號)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "sellerMemrId", referencedColumnName = "menId", insertable = false, updatable = false),
        @JoinColumn(name = "propId", referencedColumnName = "propId", insertable = false, updatable = false)
    })
    private MemberPropInventory memberPropInventory;

    private int amount;
    private int quantity;
    private Date saleTime;
    private Date expiryTime;
    private byte orderStatus;
}
