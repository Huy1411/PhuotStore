package com.example.phuotstore.service.impl;

import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.repository.ComboRepository;
import com.example.phuotstore.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private ComboRepository comboRepository;

    @Override
    public List<Combo> getAllCombos() {
        try {
            List<Combo> combos = comboRepository.getAllCombos();
            return combos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Combo> getCombosByStatus(int status) {
        try {
            List<Combo> combos = comboRepository.getCombosByStatus(status);
            return combos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Combo getComboByID(int comboID) {
        try {
            Combo combo = comboRepository.findById(comboID).get();
            return combo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveCombo(Combo combo) {
        try {
//            Set<Product> products = new HashSet<>();
//            if (products == null) {
//                return false;
//            } else {
//                products.forEach((product) -> {
//                    products.add(product);
//                });
//                combo.setProducts(products);
                comboRepository.save(combo);
                return true;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCombo(Combo combo) {
        try {
//            Set<Product> products = new HashSet<>();
//            if (products == null) {
//                return false;
//            } else {
//                products.forEach((product) -> {
//                    products.add(product);
//                });
//                combo.setProducts(products);
                comboRepository.save(combo);
                return true;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCombo(int comboID) {
        try {
            Combo combo = comboRepository.findById(comboID).get();
            comboRepository.delete(combo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Combo> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.comboRepository.findPaginateCombosStatus(pageable);
    }

    @Override
    public Page<Combo> findPaginatedShow(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.comboRepository.findPaginateCombosStatusShow(pageable);
    }

    @Override
    public Page<Combo> findPaginatedHidden(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.comboRepository.findPaginateCombosStatusHidden(pageable);
    }

    @Override
    public boolean checkComboName(String comboName) {
        Combo combo = comboRepository.findByComboName(comboName);
        if (combo == null) {
            return true;
        } else {
            return false;
        }
    }
}
