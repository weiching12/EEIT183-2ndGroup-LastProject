package com.playcentric.service.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playcentric.model.event.Event;
import com.playcentric.model.event.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepo;
	
//	建新活動
	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}

//	刪除活動
	public boolean deleteEvent(int eventId) {
//		檢查活動是否存在
		if (eventRepo.existsById(eventId)) {
			eventRepo.deleteById(eventId);
			return true;
		}
		return false;
	}
	
//	修改活動
	public Event updateEvent(Event event) {
		if (eventRepo.existsById(event.getEventId())) {
			return eventRepo.save(event);
		}
		return null;
	}
	
//	查詢所有活動
	public List<Event> getAllEvents(){
		return eventRepo.findAll();
	}
//	根據活動ID查詢活動
    public Event getEventById(int eventId) {
        Optional<Event> optionalEvent = eventRepo.findById(eventId);
        
        if (optionalEvent.isPresent()) {
            return optionalEvent.get();
        }
        
        return null;
    }
	
//  根據活動名稱進行模糊查詢
    public List<Event> getEventsByName(String eventName) {
        return eventRepo.findByEventNameContaining(eventName);
    }	
//	根據活動類型查詢活動
    public List<Event> getEventsByType(String eventType) {
        return eventRepo.findByEventType(eventType);
    }
	
//  根據活動年份和月份進行查詢
    public List<Event> getEventsByYearAndMonth(int year, int month) {
        return eventRepo.findByYearAndMonth(year, month);
    }
	
}
