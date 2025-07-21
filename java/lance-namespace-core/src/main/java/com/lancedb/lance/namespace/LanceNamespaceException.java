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

public class LanceNamespaceException extends RuntimeException {

  private final ErrorResponse errorResponse;

  public LanceNamespaceException(ApiException e) {
    // TODO: properly parse into ErrorResponse model
    super(e.getResponseBody(), e);

    this.errorResponse = new ErrorResponse();
    errorResponse.setStatus(e.getCode());
    errorResponse.type("/errors/api-exception");
    errorResponse.setTitle("Api Exception");
    errorResponse.setInstance("/v1/namespaces");
    errorResponse.setDetail(e.getResponseBody());
  }

  public LanceNamespaceException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

  public int getCode() {
    return errorResponse.getStatus();
  }

  public ErrorResponse getErrorResponse() {
    return errorResponse;
  }

  public static LanceNamespaceException badRequest(String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/bad-request");
    errorResponse.setTitle("Malformed request");
    errorResponse.setStatus(400);
    errorResponse.setDetail(detail);
    errorResponse.setInstance("/v1/namespaces");
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException unauthorized(String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/unauthorized-request");
    errorResponse.setTitle("No valid authentication credentials for the operation");
    errorResponse.setStatus(401);
    errorResponse.setDetail(detail);
    errorResponse.setInstance("/v1/namespaces");
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException forbidden(String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/forbidden-request");
    errorResponse.setTitle("Not authorized to make this request");
    errorResponse.setStatus(403);
    errorResponse.setDetail(detail);
    errorResponse.setInstance("/v1/namespaces");
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException notFound(String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/not-found-error");
    errorResponse.setTitle("Not found Error");
    errorResponse.setStatus(404);
    errorResponse.setDetail(detail);
    errorResponse.setInstance("/v1/namespaces");
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException unsupportedOperation(String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/unsupported-operation");
    errorResponse.setTitle("The server does not support this operation");
    errorResponse.setStatus(406);
    errorResponse.setDetail(detail);
    errorResponse.setInstance("/v1/namespaces");
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException conflict(String ns, String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/conflict");
    errorResponse.setTitle("The namespace has been concurrently modified");
    errorResponse.setStatus(409);
    errorResponse.setDetail(detail);
    errorResponse.setInstance(String.format("/v1/namespaces/%s", ns));
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException serviceUnavailable(String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/service-unavailable");
    errorResponse.setTitle("Slow down");
    errorResponse.setStatus(504);
    errorResponse.setDetail(detail);
    errorResponse.setInstance("/v1/namespaces");
    return new LanceNamespaceException(errorResponse);
  }

  public static LanceNamespaceException serverError(String detail) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.type("/errors/server-error");
    errorResponse.setTitle("Internal Server Error");
    errorResponse.setStatus(500);
    errorResponse.setDetail(detail);
    errorResponse.setInstance("/v1/namespaces");
    return new LanceNamespaceException(errorResponse);
  }
}
