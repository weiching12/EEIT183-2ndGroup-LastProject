package com.playcentric.controller.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playcentric.model.event.Event;
import com.playcentric.service.event.EventService;

@Controller
@RequestMapping("/events")
public class EventController {
	 @Autowired
	    private EventService eventService;

//	    根據活動ID查詢活動
	    @GetMapping("/{id}")
	    public Event getEventById(@PathVariable int id) {
	        return eventService.getEventById(id);
	    }

//	    根據活動名稱進行模糊查詢
	    @GetMapping("/name")
	    public List<Event> getEventsByName(@RequestParam String name) {
	        return eventService.getEventsByName(name);
	    }

//	    根據活動類型進行查詢
	    @GetMapping("/type")
	    public List<Event> getEventsByType(@RequestParam int type) {
	        return eventService.getEventsByType(type);
	    }

//	    根據活動年份和月份進行查詢
	    @GetMapping("/date")
	    public List<Event> getEventsByYearAndMonth(@RequestParam int year, @RequestParam int month) {
	        return eventService.getEventsByYearAndMonth(year, month);
	    }

//	    更新活動信息
	    @PutMapping("/")
	    public Event updateEvent(@RequestBody Event event) {
	        return eventService.updateEvent(event);
	    }

//	    
	    @DeleteMapping("/{id}")
	    public boolean deleteEvent(@PathVariable int id) {
	        return eventService.deleteEvent(id);
	    }

}
