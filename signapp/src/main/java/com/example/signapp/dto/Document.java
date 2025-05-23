package com.example.signapp.dto;

import lombok.Data;

@Data
public class Document {
    private int documentNo;
    private String documentTitle;
    private String documentContent;
    private int employeeId;
    private String employeeName;
}
