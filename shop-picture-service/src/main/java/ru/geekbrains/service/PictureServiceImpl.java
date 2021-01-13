package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.Picture;
import ru.geekbrains.persists.entities.PictureRef;
import ru.geekbrains.persists.repositories.PictureRefRepository;

import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService{

    private PictureRefRepository pictureRefRepository;

    public PictureServiceImpl(PictureRefRepository pictureRefRepository) {
        this.pictureRefRepository = pictureRefRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(Long id) {
        return pictureRefRepository.findById(id)
                .map(PictureRef::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureById(Long id) {
        return pictureRefRepository.findById(id)
                .map(pictureRef -> pictureRef.getPicture().getData());
    }

    @Override
    public Picture createPicture(byte[] picture) {
        return new Picture(picture);
    }
}
