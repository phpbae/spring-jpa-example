package com.phpbae.jpa.business.service;

import com.phpbae.jpa.business.domain.Part;
import com.phpbae.jpa.dao.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public Part getPart() {
        return partRepository
                .findById(1)
                .orElse(null);
    }

    public List<Part> getParts() {
        return partRepository
                .findAll();
    }

    public List<Part> getPartsByJPQL() {
        return partRepository
                .getParts();
    }
}
