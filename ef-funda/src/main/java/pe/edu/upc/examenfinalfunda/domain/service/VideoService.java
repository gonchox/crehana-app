package pe.edu.upc.examenfinalfunda.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pe.edu.upc.examenfinalfunda.domain.model.Video;

public interface VideoService {
    Page<Video> getAllVideosByCourseId(Long courseId, Pageable pageable);

    Video getVideoByIdAndCourseId(Long courseId, Long videoId);
    
    ResponseEntity<?> deleteVideo(Long courseId, Long videoId);

    Video updateVideo(Long courseId, Long videoId, Video videoRequest);

    Video createVideo(Long courseId, Video video);

    Video getVideoById(Long videoId);

    Page<Video> getAllVideos(Pageable pageable);

}
