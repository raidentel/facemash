package com.facemash.cat.service;

import com.facemash.cat.exception.ServiceUnavailableException;
import com.facemash.cat.feign.CatClient;
import com.facemash.cat.feign.model.CatApiModel;
import com.facemash.cat.mapper.CatMapper;
import com.facemash.cat.model.CatEntity;
import com.facemash.cat.repository.CatRepository;
import com.facemash.cat.web.model.Cat;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("squid:UnusedPrivateMethod")
public class CatService {

    /**
     * Cat Mapper.
     */
    private CatMapper catMapper;

    private CatRepository catRepository;

    /**
     * Cat client.
     */
    private CatClient catClient;

    public CatService(CatMapper catMapper, CatRepository catRepository, CatClient catClient) {
        this.catMapper = catMapper;
        this.catRepository = catRepository;
        this.catClient = catClient;
    }

    /**
     * Get all cats from 'https://latelier.co'.
     */
    public List<CatEntity> getCatsFromAtelierClient() {

        final CatApiModel cats = catClient.getCats();
        if (cats != null) {
            return cats.getImages();
        }
        throw new ServiceUnavailableException();
    }

    /**
     * Get all cats.
     */
    public List<Cat> getAllCats() {
        return catMapper.mapListCatEntityToCats(catRepository.findAll());
    }

    /**
     * Create cats in database.
     */
    public void saveCats(List<CatEntity> catEntities) {

        catRepository.save(catEntities);

    }

    /**
     * Get two cats.
     */
    public Iterable<Cat> getCats() {

        List<CatEntity> catEntities = catRepository.recupererLesChatsParDeux();
        if (CollectionUtils.isEmpty(catEntities) || catEntities.size() == 1) {
            catRepository.resetData();
        }
        return catMapper.mapListCatEntityToCats(catEntities);
    }

    /**
     * Vote on a cat.
     */
    public Iterable<Cat> vote(Cat cat) {
        if (cat != null) {
            cat.setVoted(true);
            cat.setScore(cat.getScore() + 1);
            catRepository.save(catMapper.mapCatToCatEntity(cat));
        }

        return getCats();
    }
}
