package com.example.backendapi.controller;

import com.example.backendapi.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * PUBLIC_INTERFACE
 * Reporting endpoints that provide downloadable PDF and CSV files.
 */
@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "PDF/CSV report exports")
public class ReportsController {

    private final ReportService reportService;

    public ReportsController(ReportService reportService) { this.reportService = reportService; }

    // PUBLIC_INTERFACE
    @GetMapping(value = "/employees.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Employees PDF", description = "Generate a PDF report listing employees.")
    public ResponseEntity<byte[]> employeesPdf() throws Exception {
        byte[] pdf = reportService.generateEmployeesPdf();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // PUBLIC_INTERFACE
    @GetMapping(value = "/employee/{id}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Employee PDF", description = "Generate a PDF report for a specific employee's metrics.")
    public ResponseEntity<byte[]> employeePdf(@PathVariable("id") String employeeId) throws Exception {
        byte[] pdf = reportService.generateEmployeePdf(employeeId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employee-" + employeeId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // PUBLIC_INTERFACE
    @GetMapping(value = "/metrics.csv", produces = "text/csv")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Metrics CSV", description = "Generate a CSV export of all metrics.")
    public ResponseEntity<byte[]> metricsCsv() {
        byte[] csv = reportService.generateMetricsCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=metrics.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }
}
