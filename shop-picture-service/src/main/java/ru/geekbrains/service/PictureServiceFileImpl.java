package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.Picture;
import ru.geekbrains.persists.entities.PictureRef;
import ru.geekbrains.persists.repositories.PictureRefRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@ConditionalOnProperty(value = "picture.storage.type", havingValue = "file")
public class PictureServiceFileImpl implements PictureService{

    @Value("${picture.storage.path}")
    private String storagePath;

    private PictureRefRepository pictureRefRepository;


    public PictureServiceFileImpl(PictureRefRepository pictureRefRepository) {
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
                .filter(pictureRef -> pictureRef.getPicture().getFilename() != null)
                .map( pictureRef -> Paths.get(storagePath, pictureRef.getPicture().getFilename()))
                .filter(Files::exists)
                .map(path -> {
                    try {
                        return Files.readAllBytes(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

    }

    @Override
    public Picture createPicture(byte[] picture) {
        String filename = UUID.randomUUID().toString();
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(storagePath,filename))){
            outputStream.write(picture);
        }catch (IOException ex){
            throw  new RuntimeException(ex);
        }
        return new Picture(filename);
    }
}
