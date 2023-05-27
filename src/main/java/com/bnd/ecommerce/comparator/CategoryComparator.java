package com.bnd.ecommerce.comparator;

import com.bnd.ecommerce.entity.Category;
import java.util.Comparator;

public class CategoryComparator implements Comparator<Category> {
  @Override
  public int compare(Category o1, Category o2) {
    return Integer.compare(o1.getId(),o2.getId());
  }
}
