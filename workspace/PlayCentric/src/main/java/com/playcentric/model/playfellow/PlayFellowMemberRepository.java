package com.playcentric.model.playfellow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayFellowMemberRepository extends JpaRepository<PlayFellowMember, Integer> {
	
	@Query("SELECT COUNT(p) > 0 FROM PlayFellowMember p WHERE p.pfnickname = :pfnickname")
	boolean existsByPfnickname(String pfnickname);//檢查暱稱是否重複

}
