package com.playcentric.model.event;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

	private Integer eventId;

	private String eventName;

	private String eventDescription;

	private Integer eventType;

	private String eventLocation;

	private LocalDateTime eventStartTime;

	private LocalDateTime eventEndTime;

	private LocalDateTime eventSignupDeadline;

	private Integer eventQuotaLimit;

}
