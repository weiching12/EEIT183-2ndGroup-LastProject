package com.playcentric.model.playfellow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayFellowMemberRepository extends JpaRepository<PlayFellowMember, Integer> {
    PlayFellowMember findByPfNickname(String pfNickname); //找nickname 方便controller檢查是否有重複暱稱
}
