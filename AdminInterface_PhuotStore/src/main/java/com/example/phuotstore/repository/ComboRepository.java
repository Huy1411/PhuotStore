package com.example.phuotstore.repository;

import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComboRepository extends JpaRepository<Combo, Integer> {

    Boolean existsByComboName(String comboName);

    Boolean existsByComboCode(String comboCode);

    @Query("SELECT p FROM Combo p WHERE p.comboID = ?1")
    Combo findByID(int comboID);

    @Query("SELECT cb FROM Combo cb WHERE cb.comboID = ?1")
    Optional<Combo> findComboByID(Integer comboID);

    @Query("SELECT cb FROM Combo cb")
    Page<Combo> getAllCombos(Pageable pageable);

    @Query("SELECT p FROM Combo p")
    List<Combo> getAllCombos();

    @Query("SELECT p FROM Combo p WHERE p.status = 'SHOW' OR p.status = 'HIDDEN'")
    List<Combo> getCombosByStatus(int status);

    @Query("SELECT p FROM Combo p WHERE p.status = 'SHOW' OR p.status = 'HIDDEN'")
    Page<Combo> findPaginateCombosStatus(Pageable pageable);

    @Query("SELECT p FROM Combo p WHERE p.status = 'SHOW' ")
    Page<Combo> findPaginateCombosStatusShow(Pageable pageable);

    @Query("SELECT p FROM Combo p WHERE p.status = 'HIDDEN' ")
    Page<Combo> findPaginateCombosStatusHidden(Pageable pageable);

    @Query("SELECT p FROM Combo p WHERE p.comboName = ?1")
    Combo findByComboName(String comboName);

}
