package com.torres.main.controller;

import com.torres.main.domain.Video;
import com.torres.main.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "videos", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class VideoController {

    private final VideoService service;

    @GetMapping
    List<Video> all() {
        return service.all();
    }
    
    @PostMapping
    Video create(@Valid @RequestBody Video newVideo) {
        return service.create(newVideo);
    }

    @GetMapping("/{id}")
    Video one(@NotBlank @PathVariable String id) {
        return service.one(id);
    }

    @PutMapping("/{id}")
    Video update(@Valid @RequestBody Video newVideo, @PathVariable String id) {
        return service.update(newVideo, id);
    }

    @DeleteMapping("{id}")
    void delete(@NotBlank @PathVariable String id) {
        service.delete(id);
    }
}