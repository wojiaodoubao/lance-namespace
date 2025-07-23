/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lancedb.lance.namespace;

import com.lancedb.lance.namespace.client.apache.ApiException;
import com.lancedb.lance.namespace.model.ErrorResponse;
import com.lancedb.lance.namespace.util.JsonUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LanceNamespaceException extends RuntimeException {
  private static final Logger LOG = LoggerFactory.getLogger(LanceNamespaceException.class);

  private final int code;
  private final String responseBody;
  private final Optional<ErrorResponse> errorResponse;

  public LanceNamespaceException(int code, String responseBody) {
    this.code = code;
    this.responseBody = responseBody;
    this.errorResponse = Optional.empty();
  }

  public LanceNamespaceException(ApiException e) {
    super(e.getResponseBody(), e);

    ErrorResponse eResp = parse(e);
    if (eResp != null) {
      this.code = 0;
      this.responseBody = null;
      this.errorResponse = Optional.of(eResp);
    } else {
      this.code = e.getCode();
      this.responseBody = e.getResponseBody();
      this.errorResponse = Optional.empty();
    }
  }

  public LanceNamespaceException(ErrorResponse errorResponse) {
    super(errorResponse.getType());

    this.code = 0;
    this.responseBody = null;
    this.errorResponse = Optional.of(errorResponse);
  }

  private static ErrorResponse parse(ApiException e) {
    if (e.getResponseBody() != null) {
      try {
        return JsonUtil.mapper().readValue(e.getResponseBody(), ErrorResponse.class);
      } catch (JsonProcessingException ex) {
        LOG.warn("Response body in wrong format. body={}", e.getResponseBody(), ex);
      }
    }

    return null;
  }

  public int getCode() {
    return errorResponse.isPresent() ? errorResponse.get().getStatus() : code;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public Optional<ErrorResponse> getErrorResponse() {
    return errorResponse;
  }

  public static LanceNamespaceException badRequest(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(400);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException unauthorized(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(401);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException forbidden(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(403);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException notFound(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(404);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException unsupportedOperation(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(406);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException conflict(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(409);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException serviceUnavailable(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(504);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException serverError(
      String type, String title, String instance, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type(type);
    errorResponse.setTitle(title);
    errorResponse.setStatus(500);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(instance);
    return new LanceNamespaceException(errorResponse);
  }
}
