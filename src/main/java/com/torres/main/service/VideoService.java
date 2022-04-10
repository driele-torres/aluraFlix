package com.torres.main.service;

import com.torres.main.domain.Video;
import com.torres.main.exception.VideoNotFoundException;
import com.torres.main.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoRepository repository;

    public List<Video> all() {
        return repository.findAll();
    }

    public Video create(Video newVideo) {
        return repository.save(newVideo);
    }

    public Video one(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(id));
    }

    public Video update(Video newVideo, String id) {
        return repository.findById(id)
                .map((video) -> {
                    newVideo.setId(video.getId());
                    return repository.save(newVideo);
                })
                .orElseThrow(() -> new VideoNotFoundException(id));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}