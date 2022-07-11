package com.theone.springboot.utils;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.theone.springboot.entity.Event;

@Component
public class EventCsvExporter {

	public void csvExport(Writer writer, List<Event> events) {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			csvPrinter.printRecord("廣告編號", "公司統編", "廣告圖路徑", "職缺連結", "上架日期", "下架日期", "備註", "狀態");
			for (Event event : events) {
				String state = null;
				switch (event.getState()) {
				case 0:
					state = "未審核";
					break;
				case 1:
					state = "審核通過";
					break;
				case 2:
					state = "已退件";
					break;
				case 3:
					state = "已撤銷";
					break;
				default:
					break;
				}
				csvPrinter.printRecord(event.getEventId(),
						   event.getCompId(),
						   event.getImgUrl(),
						   event.getEventLinkUrl(),
						   sdf.format(event.getPostStart()),
						   sdf.format(event.getPostEnd()),
						   event.getRemark(),
						   state);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
