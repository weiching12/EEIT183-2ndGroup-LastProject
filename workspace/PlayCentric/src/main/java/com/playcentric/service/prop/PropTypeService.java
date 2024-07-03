package com.playcentric.service.prop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playcentric.model.prop.PropType;
import com.playcentric.model.prop.PropTypeRepository;

import java.util.Optional;

@Service
public class PropTypeService {

    @Autowired
    PropTypeRepository propTypeRepo;

    public PropType findById(int propTypeId) {
        Optional<PropType> optionalPropType = propTypeRepo.findById(propTypeId);
        return optionalPropType.orElse(null); 
    }
}
