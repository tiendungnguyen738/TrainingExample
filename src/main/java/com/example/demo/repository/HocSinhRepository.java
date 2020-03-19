package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.HocSinh;

public interface HocSinhRepository extends CrudRepository<HocSinh, Long>{

	@Query("select u.ngaySinh from HocSinh u")
	List<String> layDanhSachNgaySinh();
	
	@Query("select u from HocSinh u where u.ten = :ten")
	List<HocSinh> findHocSinhByName(@Param("ten") String ten);
}
