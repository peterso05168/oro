package hk.oro.iefas.web.core.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.fasterxml.jackson.databind.ObjectMapper;

import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.core.vo.ErrorVO;

/**
 * @version $Revision: 1746 $ $Date: 2018-03-22 10:51:16 +0800 (週四, 22 三月 2018) $
 * @author $Author: marvel.ma $
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return this.hasError(this.getHttpStatusCode(response));
    }

    protected boolean hasError(HttpStatus statusCode) {
        return statusCode.series() == Series.CLIENT_ERROR || statusCode.series() == Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus httpStatus = this.getHttpStatusCode(response);
        String body = IOUtils.toString(response.getBody(), this.getCharset(response));

        ErrorVO errorVO = null;
        if (CommonUtils.isNotBlank(body)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                errorVO = mapper.readValue(body, ErrorVO.class);
            } catch (Exception e) {
                errorVO = null;
            }
        }

        if (errorVO != null) {
            throw new BusinessException(errorVO.getMessage(), errorVO.getErrorMsgs());
        } else {
            Series series = httpStatus.series();
            switch (series) {
                case CLIENT_ERROR:
                    throw new HttpClientErrorException(httpStatus, response.getStatusText(), response.getHeaders(),
                        this.getResponseBody(response), this.getCharset(response));
                case SERVER_ERROR:
                    throw new HttpServerErrorException(httpStatus, response.getStatusText(), response.getHeaders(),
                        this.getResponseBody(response), this.getCharset(response));
                default:
                    throw new RestClientException("Unknown status code [" + httpStatus + "]");
            }
        }
    }

    protected HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
        try {
            return response.getStatusCode();
        } catch (IllegalArgumentException arg2) {
            throw new UnknownHttpStatusCodeException(response.getRawStatusCode(), response.getStatusText(),
                response.getHeaders(), this.getResponseBody(response), this.getCharset(response));
        }
    }

    protected byte[] getResponseBody(ClientHttpResponse response) {
        try {
            return FileCopyUtils.copyToByteArray(response.getBody());
        } catch (IOException arg2) {
            return new byte[0];
        }
    }

    protected Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharset() : null;
    }

}
