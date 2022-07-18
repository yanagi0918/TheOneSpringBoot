package com.theone.springboot.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.theone.springboot.entity.Company;

@Component
public class CompanyCsvExporter {

	public void csvExport(Writer writer, List<Company> companies) {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("公司統編", "公司名稱", "產業", "資本額");
			for (Company company : companies) {
				csvPrinter.printRecord(
						company.getCompid(),
						company.getCorpname(),
						company.getIndustry(),
						company.getCapital()
						);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
