package com.playcentric.model.prop.sellOrder;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.playcentric.model.prop.MemberPropInventory.MemberPropInventory;
import com.playcentric.model.prop.MemberPropInventory.MemberPropInventoryDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class PropSellOrderDto {
    private int orderId;
    private int propId;
    private int sellerMemId;
    private MemberPropInventoryDto memberPropInventoryDto;
    private int amount;
    private int quantity;
    private String  saleTime;
    private String  expiryTime;
    private byte orderStatus;
}
