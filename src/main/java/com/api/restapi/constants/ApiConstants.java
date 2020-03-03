package com.api.restapi.constants;

public class ApiConstants {
    public static final String REGEX_FOR_UUID = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
    public static final String REGEX_FOR_NUMBERS = "^\\d+$";
    public static final String MESSAGE_FOR_REGEX_UUID_MISMATCH = "ID should be uuid format";
    public static final String MESSAGE_FOR_REGEX_NUMBER_MISMATCH = "ID should contains integers only";
    public static final String MESSAGE_FOR_INVALID_PARAMETERS_ERROR = "Invalid Parameters";
    public static final String MESSAGE_FOR_INVALID_BODY_ERROR = "Invalid Method Body. Check JSON Objects";
    public static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
}
