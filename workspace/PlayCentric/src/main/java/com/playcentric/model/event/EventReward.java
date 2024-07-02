package com.playcentric.model.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eventReward")
public class EventReward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rewardId;

	@ManyToOne
	@JoinColumn(name = "eventId", nullable = false)
	private Event event;

	private String rewardName;

	private String rewardDescription;

	@ManyToOne
	@JoinColumn(name = "rewardType", nullable = false)
	private EventRewardType eventRewardType;

	private int rewardQuantity;

}
