package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HocSinh")
public class HocSinh {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="ten")
	private String ten;
	
	@Column(name="soDienThoai")
	private String soDienThoai;
	
	@Column(name="ngaySinh")
	private String ngaySinh;
	
	@Column(name="diaChi")
	private String diaChi;
	
	public HocSinh(long id, String ten, String soDienThoai, String ngaySinh, String diaChi) {
		super();
		this.id = id;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
	}
	
	public HocSinh() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	
}
