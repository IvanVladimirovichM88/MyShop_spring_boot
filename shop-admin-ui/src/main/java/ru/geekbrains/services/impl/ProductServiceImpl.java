package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.persists.entities.PictureRef;
import ru.geekbrains.persists.entities.Product;
import ru.geekbrains.persists.repositories.CategoryRepository;
import ru.geekbrains.persists.repositories.ProductRepository;
import ru.geekbrains.service.PictureService;
import ru.geekbrains.services.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PictureService pictureService;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            PictureService pictureService) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.pictureService = pictureService;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public ProductData getProductDataById(Long id) {
        return new ProductData(productRepository.getOne(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductData> getAllProductsData() {
        return productRepository.findAll().stream().map(ProductData::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long id) {

        productRepository.getOne(id).getPictureRefs()
                .forEach(pictureRef -> pictureService.removePictureById(pictureRef.getId()));

        productRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public Product createOrUpdateProduct(ProductData productData) {
        Product product = productData.getId() == null ?
                            new Product() :
                            productRepository.getOne(productData.getId());

        product.setTitle(productData.getTitle());
        product.setPrice(productData.getPrice());
        product.setDescription(productData.getDescription());
        product.setCategories(productData.getCategoryTitle().stream()
                .map(categoryRepository::findByTitle)
                .collect(Collectors.toList()));

        if (productData.getNewPictures() != null){
            for (MultipartFile newPicture : productData.getNewPictures()){
                try {
                    if (!newPicture.isEmpty()) {
                        PictureRef pictureRef = new PictureRef(
                                newPicture.getOriginalFilename(),
                                newPicture.getContentType(),
                                pictureService.createPicture(newPicture.getBytes()));

                        product.getPictureRefs().add(pictureRef);
                        pictureRef.setProduct(product);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return productRepository.save(product);
    }


}
