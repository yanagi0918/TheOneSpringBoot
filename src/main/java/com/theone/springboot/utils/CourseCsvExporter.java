package com.theone.springboot.utils;

import com.theone.springboot.entity.CourseBean;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CourseCsvExporter {

	public void csvExport(Writer writer, List<CourseBean> courses) {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("講師帳號", "講師", "狀態","課程編號","課程分類","評分","價錢","上架時間");
			for (CourseBean course : courses) {
				csvPrinter.printRecord(
						course.getUserid(),
						course.getLecturer(),
						course.getStatus(),
						course.getCourseNo(),
						course.getCourseCategory(),
						course.getScore(),
						course.getPrice(),
						course.getDate()
						);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
