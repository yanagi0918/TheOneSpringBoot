package com.theone.springboot.utils;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.theone.springboot.entity.Member;

@Component
public class MemberCsvExporter {

	public void csvExport(Writer writer, List<Member> members) {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			csvPrinter.printRecord("會員編號", "姓名", "性別", "生日", "住宅電話", "手機", "通訊地址", "email");
			for (Member member : members) {
				csvPrinter.printRecord(
						member.getIdNumber(),
						member.getUsername(),
						member.getGender(),
						sdf.format(member.getBirth()),
						member.getTele(),
						member.getPhone(),
						member.getAddress(),
						member.getEmail()
						);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
