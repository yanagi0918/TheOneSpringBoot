package com.theone.springboot.utils;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Resume;

@Component
public class ResumeCsvExporter {

	public void csvExport(Writer writer, List<Resume> resumes) {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("履歷編號", "履歷名稱", "身分證字號", "學歷", "畢業學校", "畢業科系", "自傳", "工作經驗","取得證照");
			for (Resume resume : resumes) {
				csvPrinter.printRecord(
						resume.getResumeId(),
						resume.getResumeName(),
						resume.getUserId(),
						resume.getEdu(),
						resume.getSchool(),
						resume.getDept(),
						resume.getAutobiography(),
						resume.getWorkExp(),
						resume.getSkills()
						);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
