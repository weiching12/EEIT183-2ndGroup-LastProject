package com.playcentric.model.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Integer> {
//    根據活動名稱進行模糊查詢
    List<Event> findByEventNameContaining(String eventName);

//    根據活動類型進行查詢
    List<Event> findByEventType(String eventType);

// 	根據活動年份和月份進行查詢
    @Query("SELECT e FROM Event e WHERE YEAR(e.eventStartTime) = :year AND MONTH(e.eventStartTime) = :month")
    List<Event> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}

