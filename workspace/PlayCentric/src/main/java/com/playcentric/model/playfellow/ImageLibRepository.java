package com.playcentric.model.playfellow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playcentric.model.ImageLib;

@Repository
public interface ImageLibRepository extends JpaRepository<ImageLib, Integer> {
}