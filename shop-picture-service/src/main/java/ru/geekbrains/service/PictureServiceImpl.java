package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.Picture;
import ru.geekbrains.persists.entities.PictureRef;
import ru.geekbrains.persists.repositories.PictureRefRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService{

    private PictureRefRepository pictureRefRepository;

    public PictureServiceImpl(PictureRefRepository pictureRefRepository) {
        this.pictureRefRepository = pictureRefRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(Long id) {
        return pictureRefRepository.findById(id).map(PictureRef::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureById(Long id) {
        Optional<String> picturePath = pictureRefRepository.findById(id)
                                        .map( pictureRef -> pictureRef.getPicture().getPath());

        if (picturePath.isPresent()){
            try {
                FileInputStream pictureFile = new FileInputStream(picturePath.get());
                byte[] buffer = new byte[pictureFile.available()];
                pictureFile.read(buffer,0,pictureFile.available());
                return Optional.of(buffer);

            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    @Override
    public Picture createPicture(byte[] picture) {
         return new Picture(picture);
    }
}
