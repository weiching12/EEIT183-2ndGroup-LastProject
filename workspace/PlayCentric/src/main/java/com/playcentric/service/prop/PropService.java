package com.playcentric.service.prop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.playcentric.model.prop.Props;
import com.playcentric.model.prop.PropsRepository;


@Service 
public class PropService {

	@Autowired
	PropsRepository propsRepo;
	
//	遊戲ID找全部道具
	public List<Props> findPropsByGameId(int gameId) {
		return propsRepo.findPropsByGameId(gameId);
	}
	
//	新增道具
	public void save(Props prop) {
		propsRepo.save(prop);
	} 
	
//	刪除道具
	public void deleteById(int id) {
		propsRepo.deleteById(id);
	}
}
