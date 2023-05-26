package com.bnd.ecommerce.service;

import com.bnd.ecommerce.entity.Tablet;

import java.util.List;

public interface TabletService {

    Tablet saveTablet(Tablet tablet);

    List<Tablet> listTablets();
}
