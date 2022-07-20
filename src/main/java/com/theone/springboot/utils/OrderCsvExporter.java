package com.theone.springboot.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.theone.springboot.entity.Order;

@Component
public class OrderCsvExporter {

	public void csvExport(Writer writer, List<Order> orders) {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("訂單編號", "訂單日期", "會員帳號", "課程名稱", "課程類別", "總價", "狀態");
			for (Order order : orders) {
				csvPrinter.printRecord(
						order.getOrderId(),
						order.getOrderDate(),
						order.getMember().getUserid(),
						order.getCourseBean().getCourseName(),
						order.getCourseBean().getCourseCategory(),
						order.getTotalPrice(),
						order.getState()
						);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
