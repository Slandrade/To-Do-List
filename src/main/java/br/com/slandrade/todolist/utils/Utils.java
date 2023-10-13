package br.com.slandrade.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

  public static void copyNonNullProperties(Object src, Object target) {
    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
  }
  
  public static String[] getNullPropertyNames(Object src) {
    final BeanWrapper wrappedSrc = new BeanWrapperImpl(src);

    PropertyDescriptor[] pds = wrappedSrc.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();

    for(PropertyDescriptor pd : pds) {
      Object srcValue = wrappedSrc.getPropertyValue(pd.getName());
      if(srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}