package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.service.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/pictures")

public class PictureController {
    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);
    private PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public void downloadProductPicture(
            @PathVariable Long pictureId,
            HttpServletResponse response
    ) throws IOException {
        logger.info("\t--> Downloading picture with id : {}",pictureId);

        Optional<String> optional = pictureService.getPictureContentTypeById(pictureId);
        if(optional.isPresent()){
            response.setContentType(optional.get());
            response.getOutputStream().write(pictureService.getPictureById(pictureId).get());
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
