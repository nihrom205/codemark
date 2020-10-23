package ru.codemark.adminka.respons;

import java.util.List;

public class Respons {
    private Boolean success;
    private List<String> errors;

    public Respons() {
    }

    public Respons(Boolean success) {
        this.success = success;
    }

    public Respons(Boolean success, List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
