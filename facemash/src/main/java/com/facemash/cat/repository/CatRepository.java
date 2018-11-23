package com.facemash.cat.repository;

import com.facemash.cat.model.CatEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CatRepository extends CrudRepository<CatEntity, String> {

    @Query(value = "Select * from cat where voted = 0  limit 2", nativeQuery = true)
    List<CatEntity> recupererLesChatsParDeux();

    @Override
    @Query(value = "Select * from cat Order By score desc ", nativeQuery = true)
    List<CatEntity> findAll();

    @Transactional
    @Modifying
    @Query(value = "Update CatEntity c set c.voted = 0")
    void resetData();



}
