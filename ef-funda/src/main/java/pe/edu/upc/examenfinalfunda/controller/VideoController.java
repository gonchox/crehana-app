package pe.edu.upc.examenfinalfunda.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinalfunda.domain.model.Video;
import pe.edu.upc.examenfinalfunda.domain.service.VideoService;
import pe.edu.upc.examenfinalfunda.resource.SaveVideoResource;
import pe.edu.upc.examenfinalfunda.resource.VideoResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "videos", description = "Videos API")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class VideoController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private VideoService videoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Videos returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/courses/{courseId}/videos")
    public Page<VideoResource> getAllVideosByCourseId(
            @PathVariable(name = "courseId") Long courseId,
            Pageable pageable) {
        Page<Video> postPage = videoService.getAllVideosByCourseId(courseId, pageable);
        List<VideoResource> resources = postPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<VideoResource>(resources, pageable, resources.size());
    }


    @GetMapping("/videos/{id}")
    public VideoResource getVideoById(
            @Parameter(description="Video Id")
            @PathVariable(name = "id") Long videoId) {
        return convertToResource(videoService.getVideoById(videoId));
    }


    @GetMapping("/courses/{courseId}/videos/{videoId}")
    public VideoResource getVideoByIdAndCourseId(@PathVariable(name = "courseId") Long courseId,
                                             @PathVariable(name = "videoId") Long videoId) {
        return convertToResource(videoService.getVideoByIdAndCourseId(courseId, videoId));
    }

    // @Operation(security={ @SecurityRequirement(name="Authorization") })
    @PostMapping("/courses/{courseId}/videos")
    public VideoResource createVideo(@PathVariable(name = "courseId") Long courseId,
                                   @Valid @RequestBody SaveVideoResource resource) {
        return convertToResource(videoService.createVideo(courseId, convertToEntity(resource)));

    }


    @PutMapping("/courses/{courseId}/videos/{videoId}")
    public VideoResource updateVideo(@PathVariable(name = "courseId") Long courseId,
                                   @PathVariable(name = "videoId") Long videoId,
                                   @Valid @RequestBody SaveVideoResource resource) {
        return convertToResource(videoService.updateVideo(courseId, videoId, convertToEntity(resource)));
    }


    @DeleteMapping("/courses/{courseId}/videos/{videoId}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "courseId") Long courseId,
                                        @PathVariable(name = "videoId") Long videoId) {
        return videoService.deleteVideo(courseId, videoId);
    }


    @Operation(summary = "Get Videos", description = "Get All Videos by Pages", tags = { "videos" })
    @GetMapping("/videos")
    public Page<VideoResource> getAllVideos(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Video> videosPage = videoService.getAllVideos(pageable);
        List<VideoResource> resources = videosPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<VideoResource>(resources,pageable , resources.size());
    }

    
    private Video convertToEntity(SaveVideoResource resource) {
        return mapper.map(resource, Video.class);
    }

    private VideoResource convertToResource(Video entity) {
        return mapper.map(entity, VideoResource.class);
    }
}
