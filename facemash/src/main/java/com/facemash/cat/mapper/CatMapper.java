package com.facemash.cat.mapper;

import com.facemash.cat.model.CatEntity;
import com.facemash.cat.web.model.Cat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatMapper {

    public Cat mapCatEntityToCat(CatEntity catEntity) {
        return new Cat()
                .setId(catEntity.getId())
                .setUrl(catEntity.getUrl())
                .setScore(catEntity.getScore())
                .setVoted(catEntity.isVoted());
    }

    public List<Cat> mapListCatEntityToCats(List<CatEntity> catEntities){
        List<Cat> cats = new ArrayList<>();
        catEntities.forEach(cat -> cats.add(this.mapCatEntityToCat(cat)));
        return cats;
    }

    public CatEntity mapCatToCatEntity(Cat cat) {
        return new CatEntity()
                .setId(cat.getId())
                .setUrl(cat.getUrl())
                .setScore(cat.getScore())
                .setVoted(cat.isVoted());
    }
}
