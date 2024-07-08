package com.playcentric.service.prop.sellOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playcentric.model.prop.sellOrder.PropSellOrder;
import com.playcentric.model.prop.sellOrder.PropSellOrderDto;
import com.playcentric.model.prop.sellOrder.PropSellOrderRepository;
import com.playcentric.model.prop.MemberPropInventory.MemberPropInventoryDto;

@Service
public class PropSellOrderService {

	@Autowired
	PropSellOrderRepository propSellOrderRepo;
	

	public List<PropSellOrderDto> findAllByGameId(int id) {
		List<PropSellOrder> propSellOrders = propSellOrderRepo.findAllByGameId(id);
		return propSellOrders.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private PropSellOrderDto convertToDto(PropSellOrder order) {
		PropSellOrderDto propSellOrderDto = new PropSellOrderDto();
		propSellOrderDto.setAmount(order.getAmount());

		// 格式化時間
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		propSellOrderDto.setExpiryTime(order.getExpiryTime().format(formatter));
		propSellOrderDto.setSaleTime(order.getSaleTime().format(formatter));

		// 如果時間到期將訂單狀態改已下架
		if (LocalDateTime.now().isBefore(order.getExpiryTime())) {
			propSellOrderDto.setOrderStatus(order.getOrderStatus());
		} else {
			{
				propSellOrderDto.setOrderStatus((byte)2);
				order.setOrderStatus((byte) 2);
				propSellOrderRepo.save(order);			}
		}
		
		//如果數量是0將訂單狀態改已售完		
		if(order.getQuantity()==0) {
			order.setOrderStatus((byte)1);
			propSellOrderRepo.save(order);			
		}
		

		// 將 MemberPropInventory 轉換為 MemberPropInventoryDto
		MemberPropInventoryDto memberPropInventoryDto = new MemberPropInventoryDto(order.getMemberPropInventory());
		propSellOrderDto.setMemberPropInventoryDto(memberPropInventoryDto);
		propSellOrderDto.setOrderId(order.getOrderId());
		propSellOrderDto.setPropId(order.getOrderId());
		propSellOrderDto.setQuantity(order.getQuantity());
		propSellOrderDto.setSellerMemId(order.getSellerMemId());
		return propSellOrderDto;
	}}
