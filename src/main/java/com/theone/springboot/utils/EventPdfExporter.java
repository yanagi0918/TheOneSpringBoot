package com.theone.springboot.utils;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.theone.springboot.entity.Event;

@Component
public class EventPdfExporter {
	
	public void pdfExport(HttpServletResponse response, List<Event> events) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Event", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {0.5f, 1.5f, 3.5f, 3.0f, 1.5f, 1.5f, 1.5f, 1.5f});
        table.setSpacingBefore(10);
        
        writeTableHeader(table);
        writeTableData(table, events);
        
        document.add(table);
        
        document.close();
		
	}
	
	private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("compId", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("imgUrl", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("eventLinkUrl", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("postStart", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("postEnd", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("remark", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("state", font));
        table.addCell(cell);
    }
	
	private void writeTableData(PdfPTable table, List<Event> events) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (Event event : events) {
            table.addCell(String.valueOf(event.getEventId()));
            table.addCell(event.getCompId());
            table.addCell(event.getImgUrl());
            table.addCell(event.getEventLinkUrl());
            table.addCell(sdf.format(event.getPostStart()));
            table.addCell(sdf.format(event.getPostEnd()));
            table.addCell(event.getRemark());
            table.addCell(String.valueOf(event.getState()));
        }
    }

}
