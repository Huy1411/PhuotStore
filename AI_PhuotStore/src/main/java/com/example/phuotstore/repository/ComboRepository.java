package com.example.phuotstore.repository;

import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboRepository extends JpaRepository<Combo, Integer> {

    @Query("SELECT p FROM Combo p")
    List<Combo> getAllCombos();

    @Query("SELECT p FROM Combo p WHERE p.status = 1 OR p.status = 2")
    List<Combo> getCombosByStatus(int status);

    @Query("SELECT p FROM Combo p WHERE p.status = 1 OR p.status = 2")
    Page<Combo> findPaginateCombosStatus(Pageable pageable);

    @Query("SELECT p FROM Combo p WHERE p.status = 1 ")
    Page<Combo> findPaginateCombosStatusShow(Pageable pageable);

    @Query("SELECT p FROM Combo p WHERE p.status = 2 ")
    Page<Combo> findPaginateCombosStatusHidden(Pageable pageable);

    @Query("SELECT p FROM Combo p WHERE p.comboName = ?1")
    Combo findByComboName(String comboName);

}
