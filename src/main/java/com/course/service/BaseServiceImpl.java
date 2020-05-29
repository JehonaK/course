package com.course.service;

import com.course.entity.BaseEntity;
import com.course.repository.BaseRepository;
import com.course.type.RecordStatus;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class BaseServiceImpl<T extends BaseEntity<U>, U> {
    private BaseRepository<T, U> baseRepository;

    public BaseServiceImpl(BaseRepository<T, U> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public T save(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Invalid argument: " + t);
        }
        return baseRepository.save(t);
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public T update(T t, U u) throws NotFoundException {
        if (t == null) {
            throw new IllegalArgumentException(String.format("One of the arguments is invalid: %s", t));
        }
        T foundT = findById(u);
        if (foundT == null) {
            throw new NotFoundException("Entity not found");
        }

        BeanUtils.copyProperties(t, foundT, getNullPropertyNames(t));
        return foundT;
    }

    @Transactional
    public void remove(U u) throws NotFoundException {
        if (u == null) {
            throw new IllegalArgumentException("Invalid argument" + u);
        }
        T t = findById(u);
        if (t == null) {
            throw new NotFoundException("Entity not found" + t);
        }
        baseRepository.delete(t);
    }

    public T findById(U u) {
        if (u == null) {
            throw new IllegalArgumentException("Invalid argument: " + u);
        }
        Optional<T> optionalT = baseRepository.findById(u);

        return optionalT.orElse(null);

    }

    public List<T> findAllSorted(String direction, String... properties) {

        if (!Arrays.asList("ASC", "DESC").contains(direction.toUpperCase())) {
            throw new IllegalArgumentException("Value must be either ASC or DESC: " + direction);
        }

        return baseRepository.findAll(Sort.by(Sort.Direction.valueOf(direction), properties));
    }

    protected static <T> String[] getNullPropertyNames(T source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
