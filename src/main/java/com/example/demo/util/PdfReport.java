package com.example.demo.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.domain.HocSinh;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReport extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"danh_sach_hoc_sinh.pdf\"");
		
		@SuppressWarnings("unchecked")
		List<HocSinh> dsHocSinh = (List<HocSinh>) model.get("dsHocSinh");
		
		Table table = new Table(4);
		table.addCell("Tên Học Sinh");
		table.addCell("Số Điện Thoại");
		table.addCell("Ngày Sinh");
		table.addCell("Địa Chỉ");
		
		for (HocSinh hocSinh : dsHocSinh) {
			table.addCell(hocSinh.getTen());
			table.addCell(hocSinh.getSoDienThoai());
			table.addCell(hocSinh.getNgaySinh());
			table.addCell(hocSinh.getDiaChi());
		}
		
		document.add(table);
	}

}
