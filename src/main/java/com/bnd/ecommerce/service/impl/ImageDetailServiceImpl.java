package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.entity.ImageDetail;
import com.bnd.ecommerce.repository.ImageDetailRepository;
import com.bnd.ecommerce.service.ImageDetailService;
import org.springframework.stereotype.Service;

@Service
public class ImageDetailServiceImpl implements ImageDetailService {

    private final ImageDetailRepository imageDetailRepository;

    public ImageDetailServiceImpl(ImageDetailRepository imageDetailRepository) {
        this.imageDetailRepository = imageDetailRepository;
    }

    @Override
    public ImageDetail save(ImageDetail imageDetail) {
        return imageDetailRepository.save(imageDetail);
    }
}
