package by.mmarshal.app.bootstrap.utils;

import by.mmarshal.app.bootstrap.exceptions.RequestParamNullException;

public class HttpsRequestParamValidator {
    private static final String REQUEST_PARAMETER_IS_NULL_ERROR = "Request parameter is not initialized";
    public static void validateParamNotNull(String param) throws RequestParamNullException {
        if (param == null){
            throw new RequestParamNullException(REQUEST_PARAMETER_IS_NULL_ERROR);
        }
    }
}
