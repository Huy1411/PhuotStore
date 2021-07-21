package com.example.phuotstore.service;

import com.example.phuotstore.dto.ComboDTO;
import com.example.phuotstore.model.Combo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComboService {
    List<Combo> getAllCombos();
    List<Combo> getCombosByStatus(int status);
    Combo getComboByID(int comboID);
    boolean saveCombo(Combo combo);
    boolean updateCombo(Combo combo);
    boolean deleteCombo(int comboID);
    Page<Combo> findPaginated(int pageNo, int pageSize);
    Page<Combo> findPaginatedShow(int pageNo,int pageSize);
    Page<Combo> findPaginatedHidden(int pageNo,int pageSize);
    boolean checkComboName(String comboName);
}
