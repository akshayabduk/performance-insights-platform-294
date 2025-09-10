package com.example.backendapi.service;

import com.example.backendapi.model.Employee;
import com.example.backendapi.model.PerformanceMetric;
import com.example.backendapi.repository.EmployeeRepository;
import com.example.backendapi.repository.MetricRepository;
import com.opencsv.CSVWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PUBLIC_INTERFACE
 * Reporting service that generates PDFs and CSVs.
 */
@Service
public class ReportService {

    private final EmployeeRepository employeeRepository;
    private final MetricRepository metricRepository;

    public ReportService(EmployeeRepository employeeRepository, MetricRepository metricRepository) {
        this.employeeRepository = employeeRepository;
        this.metricRepository = metricRepository;
    }

    // PUBLIC_INTERFACE
    public byte[] generateEmployeesPdf() throws Exception {
        List<Employee> employees = employeeRepository.findAll()
                .stream().sorted(Comparator.comparing(Employee::getFullName)).toList();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);
            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
                cs.newLineAtOffset(50, 750);
                cs.showText("Employees Report");
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(0, -20);
                for (Employee e : employees) {
                    cs.showText("- " + e.getFullName() + " | " + e.getDepartment() + " | " + e.getTitle() + " | Status: " + e.getStatus());
                    cs.newLineAtOffset(0, -14);
                }
                cs.endText();
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return out.toByteArray();
        }
    }

    // PUBLIC_INTERFACE
    public byte[] generateEmployeePdf(String employeeId) throws Exception {
        Employee e = employeeRepository.findById(employeeId).orElseThrow();
        List<PerformanceMetric> metrics = metricRepository.findByEmployeeId(employeeId)
                .stream().sorted(Comparator.comparing(PerformanceMetric::getTimestamp)).toList();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);
            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
                cs.newLineAtOffset(50, 750);
                cs.showText("Employee Report: " + e.getFullName());
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(0, -20);
                DateTimeFormatter fmt = DateTimeFormatter.ISO_INSTANT;
                for (PerformanceMetric m : metrics) {
                    String line = "- " + m.getMetricType() + " | Score: " + m.getScore()
                            + " | " + fmt.format(m.getTimestamp()) + (m.getNote() != null ? (" | " + m.getNote()) : "");
                    cs.showText(line);
                    cs.newLineAtOffset(0, -14);
                }
                cs.endText();
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return out.toByteArray();
        }
    }

    // PUBLIC_INTERFACE
    public byte[] generateMetricsCsv() {
        List<PerformanceMetric> metrics = metricRepository.findAll()
                .stream().sorted(Comparator.comparing(PerformanceMetric::getTimestamp)).toList();

        StringWriter sw = new StringWriter();
        try (CSVWriter writer = new CSVWriter(sw)) {
            writer.writeNext(new String[]{"id", "employeeId", "metricType", "score", "timestamp", "note"});
            metrics.forEach(m -> writer.writeNext(new String[]{
                    m.getId(), m.getEmployeeId(), String.valueOf(m.getMetricType()), String.valueOf(m.getScore()),
                    m.getTimestamp().toString(), m.getNote() == null ? "" : m.getNote()
            }));
        }
        return sw.toString().getBytes();
    }
}
