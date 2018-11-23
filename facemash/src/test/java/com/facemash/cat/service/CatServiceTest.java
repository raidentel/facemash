package com.facemash.cat.service;


import com.facemash.cat.exception.ServiceUnavailableException;
import com.facemash.cat.feign.CatClient;
import com.facemash.cat.feign.model.CatApiModel;
import com.facemash.cat.mapper.CatMapper;
import com.facemash.cat.model.CatEntity;
import com.facemash.cat.repository.CatRepository;
import com.facemash.cat.web.model.Cat;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatServiceTest {

    @Mock
    private CatClient catClient;

    @Mock
    private CatRepository catRepository;

    @Mock
    private CatMapper catMapper;

    @InjectMocks
    private CatService catService;

    private CatEntity catEntity;
    private Cat cat;
    private List<Cat> cats;
    private List<CatEntity> catEntities;

    @Before
    public void init() {
        catEntity = new CatEntity().setId("id").setScore(1).setUrl("http://url").setVoted(false);
        cat = new Cat().setId("id").setScore(1).setUrl("http://url").setVoted(false);
        cats = Lists.newArrayList(cat);
        catEntities = Lists.newArrayList(catEntity);
    }

    @Test
    public void getCatsFromAtelierClient() {

        // Prepare

        final CatApiModel catApiModel = new CatApiModel().setImages(Lists.newArrayList(catEntity));
        doReturn(catApiModel).when(catClient).getCats();

        // Execution
        final List<CatEntity> result = catService.getCatsFromAtelierClient();

        // Verification
        verify(catClient).getCats();
        assertThat(result).isNotEmpty();
        assertThat(result.stream().findFirst().get()).isEqualToComparingFieldByField(catEntity);
    }

    @Test(expected = ServiceUnavailableException.class)
    public void getCatsFromAtelierClient_whenWebServiceIsUnavailable_shouldThrowAnException() {

        // Prepare
        doReturn(null).when(catClient).getCats();

        // Execution // Verification
        catService.getCatsFromAtelierClient();
        verify(catClient).getCats();

    }

    @Test
    public void getAllCats() {

        // Prepare
        doReturn(catEntities).when(catRepository).findAll();
        doReturn(cats).when(catMapper).mapListCatEntityToCats(catEntities);

        // Execution
        final List<Cat> res = catService.getAllCats();

        // Verification
        assertThat(res).isNotEmpty();
        verify(catRepository).findAll();
        verify(catMapper).mapListCatEntityToCats(catEntities);
    }

    @Test
    public void saveCats(){
        // Prepare
        doReturn(catEntities).when(catRepository).save(catEntities);

        // Execution // Verification
        catService.saveCats(catEntities);
        verify(catRepository).save(catEntities);
    }

    @Test
    public void getCats(){

        // Prepare
        catEntities.add(catEntity.setId("id2"));
        cats.add(cat.setId("id2"));
        doReturn(catEntities).when(catRepository).recupererLesChatsParDeux();
        doReturn(cats).when(catMapper).mapListCatEntityToCats(catEntities);


        // Execution
        final Iterable<Cat> res = catService.getCats();

        // Verification
        assertThat(res).isNotEmpty();
        assertThat(res).size().isEqualTo(2);
        verify(catRepository).recupererLesChatsParDeux();
        verify(catMapper).mapListCatEntityToCats(catEntities);


    }

    @Test
    public void getCats_whenListNotContaintTwoCats_mustResetDataInDataBase(){

        // Prepare
        doReturn(catEntities).when(catRepository).recupererLesChatsParDeux();
        doReturn(cats).when(catMapper).mapListCatEntityToCats(catEntities);
        doNothing().when(catRepository).resetData();


        // Execution
        final Iterable<Cat> res = catService.getCats();

        // Verification
        assertThat(res).isNotEmpty();
        assertThat(res).size().isEqualTo(1);
        verify(catRepository).recupererLesChatsParDeux();
        verify(catMapper).mapListCatEntityToCats(catEntities);
        verify(catRepository).resetData();

    }

    @Test
    public void vote(){

        // Preparation
        catEntity.setScore(catEntity.getScore()+1);
        catEntity.setVoted(true);
        cat.setScore(cat.getScore()+1);
        cat.setVoted(true);
        doReturn(catEntity).when(catRepository).save(catEntity);
        doReturn(catEntity).when(catMapper).mapCatToCatEntity(cat);
        catEntities.add(catEntity.setId("id2"));
        cats.add(cat.setId("id2"));
        doReturn(catEntities).when(catRepository).recupererLesChatsParDeux();
        doReturn(cats).when(catMapper).mapListCatEntityToCats(catEntities);

        // Execution
        final Iterable<Cat> result = catService.vote(cat);

        // Verification
        assertThat(result).isNotEmpty();
        verify(catRepository).recupererLesChatsParDeux();
        verify(catRepository).save(catEntity);
        verify(catMapper).mapListCatEntityToCats(catEntities);
        verify(catMapper).mapCatToCatEntity(cat);

    }

}