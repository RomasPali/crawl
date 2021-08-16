package com.soft.crawl.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

class ReportCSV implements Report {

	@Override
	public byte[] getBytes() {
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			OutputStreamWriter streamWriter = new OutputStreamWriter(stream);

			CSVWriter writer = new CSVWriter(streamWriter);

			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "Name", "Class", "Marks" });
			data.add(new String[] { "Aman", "10", "620" });
			data.add(new String[] { "Suraj", "10", "630" });
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
