package com.cgi.library.repository;

import com.cgi.library.entity.Book;
import com.cgi.library.entity.CheckOut;
import com.cgi.library.model.CheckOutDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.PageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut, UUID> {

    @Query("SELECT c FROM CheckOut c WHERE c.dueDate < :localDate")
    List<CheckOut> findOverdueBooks(@Param("localDate") LocalDate localDate, Pageable pageable);

    List<CheckOut> findAllByReturnedDateIsNull(Pageable pageable);

    List<CheckOut> findAllByReturnedDateIsNotNull(Pageable pageable);
}
