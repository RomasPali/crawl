package com.soft.crawl.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.soft.crawl.search.entity.LinkEntity;
import com.soft.crawl.search.entity.SeedEntity;
import com.soft.crawl.search.entity.TermEntity;

/**
* ReportCSV implementation of Report interface, for generating csv file bytes 
* 
* @author Romas
* 
*/
@Service
class ReportCSV implements Report {

	@Override
	public byte[] getReportBytes(SeedEntity seedEntity) {
		
		if (seedEntity == null) {
			return null;
		}
		
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			OutputStreamWriter streamWriter = new OutputStreamWriter(stream);

			CSVWriter writer = new CSVWriter(streamWriter);

			List<String[]> data = new ArrayList<>();
			
			//First and second line
			List<String> firstLine = new ArrayList<String>();
			firstLine.add("");
			
			List<String> secondLine = new ArrayList<>();
			secondLine.add(seedEntity.getUrl());
			
			for (TermEntity termEntity : seedEntity.getTermEntities()) {
				firstLine.add(termEntity.getTerm());
				secondLine.add(String.valueOf(termEntity.getHit()));
			}

			data.add(firstLine.toArray(String[]::new));
			data.add(secondLine.toArray(String[]::new));

			//Last lines
			for (LinkEntity linkEntity : seedEntity.getLinkEntities()) {
				
				List<String> line = new ArrayList<>();
				line.add(linkEntity.getUrl());
								
				for (TermEntity term : linkEntity.getTermEntities()) {
					line.add(String.valueOf(term.getHit()));
				}
				
				data.add(line.toArray(String[]::new));
			}

			writer.writeAll(data);
			
			streamWriter.flush();
			writer.close();

			return stream.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public byte[] getReportTopBytes(SeedEntity seedEntity, int top) {
		if (seedEntity == null) {
			return null;
		}
		
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			OutputStreamWriter streamWriter = new OutputStreamWriter(stream);

			CSVWriter writer = new CSVWriter(streamWriter);

			List<String[]> data = new ArrayList<>();
			
			//First and second line
			List<String> firstLine = new ArrayList<String>();
			firstLine.add("");
			
			List<String> secondLine = new ArrayList<>();
			secondLine.add(seedEntity.getUrl());
			
			for (TermEntity termEntity : seedEntity.getTermEntities()) {
				firstLine.add(termEntity.getTerm());
				secondLine.add(String.valueOf(termEntity.getHit()));
			}

			data.add(firstLine.toArray(String[]::new));
			data.add(secondLine.toArray(String[]::new));

			//Last lines
			
			Collections.sort(seedEntity.getLinkEntities());
			
			for (int i = 0; i < top && i < seedEntity.getLinkEntities().size(); i++) {
				
				LinkEntity linkEntity = seedEntity.getLinkEntities().get(i);
				
				List<String> line = new ArrayList<>();
				line.add(linkEntity.getUrl());
								
				for (TermEntity term : linkEntity.getTermEntities()) {
					line.add(String.valueOf(term.getHit()));
				}
				
				data.add(line.toArray(String[]::new));
			}

			writer.writeAll(data);
			
			streamWriter.flush();
			writer.close();

			return stream.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
