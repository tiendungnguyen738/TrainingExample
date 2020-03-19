package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.HocSinh;
import com.example.demo.repository.HocSinhRepository;
import com.example.demo.service.HocSinhService;

@Service
public class HocSinhServiceImpl implements HocSinhService{

	@Autowired
	private HocSinhRepository hocSinhRepository;
	
	@Override
	public void luuHocSinh(HocSinh hocSinh) {
		hocSinhRepository.save(hocSinh);
	}

	@Override
	public void xoaHocSinh(Long id) {
		hocSinhRepository.deleteById(id);
	}

	@Override
	public Optional<HocSinh> layHocSinh(Long id) {
		return hocSinhRepository.findById(id);
	}

	@Override
	public List<String> layNgaySinh() {
		List<String> dsNgaySinh = hocSinhRepository.layDanhSachNgaySinh();
		return dsNgaySinh;
	}

	@Override
	public List<HocSinh> layDanhSachHocSinhTheoTenVaTuoi(String tenHocSinh, int tuoiHocSinh) {
		List<HocSinh> listHocSinhTheoTuoi = new ArrayList<HocSinh>();
		List<HocSinh> listHocSinh = hocSinhRepository.findHocSinhByName(tenHocSinh);
		int nam = Calendar.getInstance().get(Calendar.YEAR) - tuoiHocSinh;
		String namSinh = "";
		for (HocSinh hocSinh : listHocSinh) {
			namSinh = hocSinh.getNgaySinh().substring(hocSinh.getNgaySinh().length()-4, hocSinh.getNgaySinh().length());
			if(nam == Integer.parseInt(namSinh)) {
				listHocSinhTheoTuoi.add(hocSinh);
			}
		}
		
		return listHocSinhTheoTuoi;
	}
	
}
