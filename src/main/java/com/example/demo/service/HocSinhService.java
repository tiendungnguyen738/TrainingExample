package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.HocSinh;

public interface HocSinhService {
	void luuHocSinh(HocSinh hocSinh);
	void xoaHocSinh(Long id);
	Optional<HocSinh> layHocSinh(Long id);
	List<String> layNgaySinh();
	List<HocSinh> layDanhSachHocSinhTheoTenVaTuoi(String tenHocSinh,int tuoiHocSinh);
}
