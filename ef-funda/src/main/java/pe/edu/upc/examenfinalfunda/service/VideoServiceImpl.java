package pe.edu.upc.examenfinalfunda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinalfunda.domain.model.Course;
import pe.edu.upc.examenfinalfunda.domain.model.Video;
import pe.edu.upc.examenfinalfunda.domain.repository.CourseRepository;
import pe.edu.upc.examenfinalfunda.domain.repository.VideoRepository;
import pe.edu.upc.examenfinalfunda.domain.service.VideoService;
import pe.edu.upc.examenfinalfunda.exception.ResourceNotFoundException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Page<Video> getAllVideosByCourseId(Long courseId, Pageable pageable) {
        return videoRepository.findByCourseId(courseId, pageable);
    }

    @Override
    public Video getVideoByIdAndCourseId(Long courseId, Long videoId) {
        return videoRepository.findByIdAndCourseId(videoId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Video not found with Id " + videoId +
                                " and CourseId " + courseId));
    }

    @Override
    public ResponseEntity<?> deleteVideo(Long courseId, Long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video", "Id", videoId));
        videoRepository.delete(video);
        return ResponseEntity.ok().build();
    }

    @Override
    public Video updateVideo(Long courseId, Long videoId, Video videoRequest) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video", "Id", videoId));
        video.setTitle(videoRequest.getTitle());
       video.setDescription(videoRequest.getDescription());
        video.setDuration(videoRequest.getDuration());
        return videoRepository.save(video);
    }

    @Override
    public Video createVideo(Long courseId, Video video) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        video.setCourse(course);
        return videoRepository.save(video);
    }

    @Override
    public Video getVideoById(Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video", "Id", videoId));
    }

    @Override
    public Page<Video> getAllVideos(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }
}
