package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Response DTO for chart-ready time series data.
 */
public class ChartDataResponse {

    @Schema(description = "Timestamps for data points")
    private List<Instant> timestamps = new ArrayList<>();

    @Schema(description = "Values associated with timestamps")
    private List<Double> values = new ArrayList<>();

    public ChartDataResponse() {}

    public ChartDataResponse(List<Instant> timestamps, List<Double> values) {
        this.timestamps = timestamps;
        this.values = values;
    }

    public List<Instant> getTimestamps() { return timestamps; }
    public List<Double> getValues() { return values; }
    public void setTimestamps(List<Instant> timestamps) { this.timestamps = timestamps; }
    public void setValues(List<Double> values) { this.values = values; }
}
