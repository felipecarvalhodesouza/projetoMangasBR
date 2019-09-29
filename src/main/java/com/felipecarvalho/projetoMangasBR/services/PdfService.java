package com.felipecarvalho.projetoMangasBR.services;

import java.io.ByteArrayInputStream;
import java.util.Set;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {
	
	@Autowired
	TitleRepository titleRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PdfService.class);
	
	public static ByteArrayInputStream createDocument(Collection collection){
		
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Set<CollectionTitle> titles = collection.getCollectionTitle();
		
		try {
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(95);
            table.setWidths(new int[]{4, 2, 3});
            
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Título", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Editora", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Status", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            for (CollectionTitle title : titles) {
            	
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(title.getTitle().getName(), headFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(title.getTitle().getPublisher().getName()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                String state;
                if(title.getTitle().isFinished())
                	state = "Completo em ";
                else state = "Em andamento com ";
                
                cell = new PdfPCell(new Phrase(state + title.getVolumesUser().size() + " volumes"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);               
                
                
                for(VolumeUser volume : title.getVolumesUser()) {
                	int cont = 0;
                	if(volume.getDoesHave()) {
                		cell = new PdfPCell(new Paragraph(volume.getName()));
                		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                		cell.setColspan(3);
                		table.addCell(cell);
                		cont++;
                	}
                	if(cont==0) {
                		cell = new PdfPCell(new Phrase("Você não possui nenhum volume desse título"));
                		table.addCell(cell);
                	}
                }
            }
            
            
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

            
		} catch (DocumentException ex) {

			logger.error("Error occurred: {0}", ex);
		}
		
		return new ByteArrayInputStream(out.toByteArray());
		 
	}
}
