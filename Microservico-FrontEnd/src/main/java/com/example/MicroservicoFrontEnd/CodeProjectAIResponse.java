package com.example.MicroservicoFrontEnd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CodeProjectAIResponse {

    private Boolean success;
    private String message;
    private String error;
    private List<Prediction> predictions;
    private Integer count;
    private String command;
    private String moduleId;
    private String executionProvider;
    private Boolean canUseGPU;
    private Integer inferenceMs;
    private Integer processMs;
    private Integer analysisRoundTripMs;



    public static class Prediction {
        private Double x_max;
        private Double x_min;
        private Double y_max;
        private Double y_min;
        private String label;
        private Double confidence;


        public Double getX_max() {
            return x_max;
        }

        public void setX_max(Double x_max) {
            this.x_max = x_max;
        }

        public Double getX_min() {
            return x_min;
        }

        public void setX_min(Double x_min) {
            this.x_min = x_min;
        }

        public Double getY_max() {
            return y_max;
        }

        public void setY_max(Double y_max) {
            this.y_max = y_max;
        }

        public Double getY_min() {
            return y_min;
        }

        public void setY_min(Double y_min) {
            this.y_min = y_min;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Double getConfidence() {
            return confidence;
        }

        public void setConfidence(Double confidence) {
            this.confidence = confidence;
        }
    }



    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getExecutionProvider() {
        return executionProvider;
    }

    public void setExecutionProvider(String executionProvider) {
        this.executionProvider = executionProvider;
    }

    public Boolean getCanUseGPU() {
        return canUseGPU;
    }

    public void setCanUseGPU(Boolean canUseGPU) {
        this.canUseGPU = canUseGPU;
    }

    public Integer getInferenceMs() {
        return inferenceMs;
    }

    public void setInferenceMs(Integer inferenceMs) {
        this.inferenceMs = inferenceMs;
    }

    public Integer getProcessMs() {
        return processMs;
    }

    public void setProcessMs(Integer processMs) {
        this.processMs = processMs;
    }

    public Integer getAnalysisRoundTripMs() {
        return analysisRoundTripMs;
    }

    public void setAnalysisRoundTripMs(Integer analysisRoundTripMs) {
        this.analysisRoundTripMs = analysisRoundTripMs;
    }


}

