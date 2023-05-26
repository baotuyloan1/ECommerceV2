package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.entity.Tablet;
import com.bnd.ecommerce.repository.TabletRepository;
import com.bnd.ecommerce.service.TabletService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabletServiceImpl implements TabletService {

    private TabletRepository tabletRepository;

    public TabletServiceImpl(TabletRepository tabletRepository) {
        this.tabletRepository = tabletRepository;
    }

    @Override
    public Tablet saveTablet(Tablet tablet) {
        return null;
    }

    @Override
    public List<Tablet> listTablets() {
        return tabletRepository.findAll();
    }
}
