package com.facemash.cat.web;


import com.facemash.cat.service.CatService;
import com.facemash.cat.web.model.Cat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Api(tags = "Cat controller")
public class CatController {

    /**
     * Cat service.
     */
    private CatService catService;

    /**
     * Cat controller constructor.
     */
    public CatController(CatService catService) {
        this.catService = catService;
    }

    /**
     * Get all cats
     */
    @GetMapping("/")
    @ApiOperation(value = "Get cats ", notes = "Return all Cats",
            response = Cat.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<List<Cat>> getAllCats() {
        List<Cat> cats = catService.getAllCats();
        return ResponseEntity.ok(cats);
    }

    /**
     * Get two cats
     */
    @GetMapping("/cats")
    @ApiOperation(value = "Get two cats ", notes = "Return two Cats",
            response = Cat.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Iterable<Cat>> getCats() {
        Iterable<Cat> cats = catService.getCats();
        return ResponseEntity.ok(cats);
    }


    /**
     * Vote on a cat
     */
    @PostMapping("/cats/vote")
    @ApiOperation(value = "Vote on a cat", notes = "Vote on a cat.",
            response = Cat.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Iterable<Cat>> getCats(@RequestBody Cat cat) {
        Iterable<Cat> cats = catService.vote(cat);
        return ResponseEntity.ok(cats);
    }
}
