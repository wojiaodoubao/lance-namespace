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
package com.lancedb.lance.namespace.server.springboot.api;

import com.lancedb.lance.namespace.server.springboot.model.CountTableRowsRequest;
import com.lancedb.lance.namespace.server.springboot.model.CreateTableIndexRequest;
import com.lancedb.lance.namespace.server.springboot.model.CreateTableIndexResponse;
import com.lancedb.lance.namespace.server.springboot.model.CreateTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.DeleteFromTableRequest;
import com.lancedb.lance.namespace.server.springboot.model.DeleteFromTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.DeregisterTableRequest;
import com.lancedb.lance.namespace.server.springboot.model.DeregisterTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.DescribeTableIndexStatsRequest;
import com.lancedb.lance.namespace.server.springboot.model.DescribeTableIndexStatsResponse;
import com.lancedb.lance.namespace.server.springboot.model.DescribeTableRequest;
import com.lancedb.lance.namespace.server.springboot.model.DescribeTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.DropTableRequest;
import com.lancedb.lance.namespace.server.springboot.model.DropTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.ErrorResponse;
import com.lancedb.lance.namespace.server.springboot.model.InsertIntoTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.ListTableIndicesRequest;
import com.lancedb.lance.namespace.server.springboot.model.ListTableIndicesResponse;
import com.lancedb.lance.namespace.server.springboot.model.MergeInsertIntoTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.QueryTableRequest;
import com.lancedb.lance.namespace.server.springboot.model.RegisterTableRequest;
import com.lancedb.lance.namespace.server.springboot.model.RegisterTableResponse;
import com.lancedb.lance.namespace.server.springboot.model.TableExistsRequest;
import com.lancedb.lance.namespace.server.springboot.model.UpdateTableRequest;
import com.lancedb.lance.namespace.server.springboot.model.UpdateTableResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.Optional;

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    comments = "Generator version: 7.12.0")
@Validated
@Tag(name = "Table", description = "Operations that are related to a table ")
public interface TableApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * POST /v1/table/{id}/count_rows : Count rows in a table Count the number of rows in table
   * &#x60;id&#x60;
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param countTableRowsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Result of counting rows in a table (status code 200) or Indicates a bad request error.
   *     It could be caused by an unexpected request body format or other forms of request
   *     validation failure, such as invalid json. Usually serves application/json content, although
   *     in some cases simple text/plain content might be returned by the server&#39;s middleware.
   *     (status code 400) or Unauthorized. The request lacks valid authentication credentials for
   *     the operation. (status code 401) or Forbidden. Authenticated user does not have the
   *     necessary permissions. (status code 403) or A server-side problem that means can not find
   *     the specified resource. (status code 404) or The service is not ready to handle the
   *     request. The client should wait and retry. The service may additionally send a Retry-After
   *     header to indicate when to retry. (status code 503) or A server-side problem that might not
   *     be addressable from the client side. Used for server 5xx errors without more specific
   *     documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "countTableRows",
      summary = "Count rows in a table",
      description = "Count the number of rows in table `id` ",
      tags = {"Table", "Data"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Result of counting rows in a table",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Long.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/count_rows",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<Long> countTableRows(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "CountTableRowsRequest", description = "", required = true)
          @Valid
          @RequestBody
          CountTableRowsRequest countTableRowsRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/create : Create a table with the given name Create table &#x60;id&#x60; in
   * the namespace with the given data in Arrow IPC stream. The schema of the Arrow IPC stream is
   * used as the table schema. If the stream is empty, the API creates a new empty table. REST
   * NAMESPACE ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;CreateTableRequest&#x60; information in the following way: - &#x60;id&#x60;: pass through
   * path parameter of the same name - &#x60;location&#x60;: pass through header
   * &#x60;x-lance-table-location&#x60; - &#x60;properties&#x60;: pass through header
   * &#x60;x-lance-table-properties&#x60;
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC data (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @param xLanceTableLocation URI pointing to root location to create the table at (optional)
   * @param xLanceTableProperties JSON-encoded string map (e.g. { \&quot;owner\&quot;:
   *     \&quot;jack\&quot; }) (optional)
   * @return Table properties result when creating a table (status code 200) or Indicates a bad
   *     request error. It could be caused by an unexpected request body format or other forms of
   *     request validation failure, such as invalid json. Usually serves application/json content,
   *     although in some cases simple text/plain content might be returned by the server&#39;s
   *     middleware. (status code 400) or Unauthorized. The request lacks valid authentication
   *     credentials for the operation. (status code 401) or Forbidden. Authenticated user does not
   *     have the necessary permissions. (status code 403) or A server-side problem that means can
   *     not find the specified resource. (status code 404) or The service is not ready to handle
   *     the request. The client should wait and retry. The service may additionally send a
   *     Retry-After header to indicate when to retry. (status code 503) or A server-side problem
   *     that might not be addressable from the client side. Used for server 5xx errors without more
   *     specific documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "createTable",
      summary = "Create a table with the given name",
      description =
          "Create table `id` in the namespace with the given data in Arrow IPC stream.  The schema of the Arrow IPC stream is used as the table schema.     If the stream is empty, the API creates a new empty table.  REST NAMESPACE ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the `CreateTableRequest` information in the following way: - `id`: pass through path parameter of the same name - `location`: pass through header `x-lance-table-location` - `properties`: pass through header `x-lance-table-properties` ",
      tags = {"Table", "Data"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Table properties result when creating a table",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = CreateTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/create",
      produces = {"application/json"},
      consumes = {"application/vnd.apache.arrow.stream"})
  default ResponseEntity<CreateTableResponse> createTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "body", description = "Arrow IPC data", required = true) @Valid @RequestBody
          org.springframework.core.io.Resource body,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter,
      @Parameter(
              name = "x-lance-table-location",
              description = "URI pointing to root location to create the table at",
              in = ParameterIn.HEADER)
          @RequestHeader(value = "x-lance-table-location", required = false)
          Optional<String> xLanceTableLocation,
      @Parameter(
              name = "x-lance-table-properties",
              description = "JSON-encoded string map (e.g. { \"owner\": \"jack\" }) ",
              in = ParameterIn.HEADER)
          @RequestHeader(value = "x-lance-table-properties", required = false)
          Optional<String> xLanceTableProperties) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"schema\" : { \"metadata\" : { \"key\" : \"metadata\" }, \"fields\" : [ { \"metadata\" : { \"key\" : \"metadata\" }, \"nullable\" : true, \"name\" : \"name\", \"type\" : { \"length\" : 0, \"fields\" : [ null, null ], \"type\" : \"type\" } }, { \"metadata\" : { \"key\" : \"metadata\" }, \"nullable\" : true, \"name\" : \"name\", \"type\" : { \"length\" : 0, \"fields\" : [ null, null ], \"type\" : \"type\" } } ] }, \"location\" : \"location\", \"version\" : 0, \"properties\" : { \"key\" : \"properties\" }, \"storage_options\" : { \"key\" : \"storage_options\" } }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/create_index : Create an index on a table Create an index on a table column
   * for faster search operations. Supports vector indexes (IVF_FLAT, IVF_HNSW_SQ, IVF_PQ, etc.) and
   * scalar indexes (BTREE, BITMAP, FTS, etc.). Index creation is handled asynchronously. Use the
   * &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to monitor
   * index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Index created successfully (status code 200) or Indicates a bad request error. It could
   *     be caused by an unexpected request body format or other forms of request validation
   *     failure, such as invalid json. Usually serves application/json content, although in some
   *     cases simple text/plain content might be returned by the server&#39;s middleware. (status
   *     code 400) or Unauthorized. The request lacks valid authentication credentials for the
   *     operation. (status code 401) or Forbidden. Authenticated user does not have the necessary
   *     permissions. (status code 403) or A server-side problem that means can not find the
   *     specified resource. (status code 404) or The service is not ready to handle the request.
   *     The client should wait and retry. The service may additionally send a Retry-After header to
   *     indicate when to retry. (status code 503) or A server-side problem that might not be
   *     addressable from the client side. Used for server 5xx errors without more specific
   *     documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "createTableIndex",
      summary = "Create an index on a table",
      description =
          "Create an index on a table column for faster search operations. Supports vector indexes (IVF_FLAT, IVF_HNSW_SQ, IVF_PQ, etc.) and scalar indexes (BTREE, BITMAP, FTS, etc.). Index creation is handled asynchronously.  Use the `ListTableIndices` and `DescribeTableIndexStats` operations to monitor index creation progress. ",
      tags = {"Table", "Index", "Metadata"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Index created successfully",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = CreateTableIndexResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/create_index",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<CreateTableIndexResponse> createTableIndex(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(
              name = "CreateTableIndexRequest",
              description = "Index creation request",
              required = true)
          @Valid
          @RequestBody
          CreateTableIndexRequest createTableIndexRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"location\" : \"location\", \"id\" : [ \"id\", \"id\" ], \"properties\" : { \"key\" : \"properties\" } }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/delete : Delete rows from a table Delete rows from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteFromTableRequest Delete request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Delete successful (status code 200) or Indicates a bad request error. It could be
   *     caused by an unexpected request body format or other forms of request validation failure,
   *     such as invalid json. Usually serves application/json content, although in some cases
   *     simple text/plain content might be returned by the server&#39;s middleware. (status code
   *     400) or Unauthorized. The request lacks valid authentication credentials for the operation.
   *     (status code 401) or Forbidden. Authenticated user does not have the necessary permissions.
   *     (status code 403) or A server-side problem that means can not find the specified resource.
   *     (status code 404) or The service is not ready to handle the request. The client should wait
   *     and retry. The service may additionally send a Retry-After header to indicate when to
   *     retry. (status code 503) or A server-side problem that might not be addressable from the
   *     client side. Used for server 5xx errors without more specific documentation in individual
   *     routes. (status code 5XX)
   */
  @Operation(
      operationId = "deleteFromTable",
      summary = "Delete rows from a table",
      description = "Delete rows from table `id`. ",
      tags = {"Table", "Data"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Delete successful",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DeleteFromTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/delete",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<DeleteFromTableResponse> deleteFromTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "DeleteFromTableRequest", description = "Delete request", required = true)
          @Valid
          @RequestBody
          DeleteFromTableRequest deleteFromTableRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString = "{ \"version\" : 0 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/deregister : Deregister a table Deregister table &#x60;id&#x60; from its
   * namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deregisterTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Response of DeregisterTable (status code 200) or Indicates a bad request error. It
   *     could be caused by an unexpected request body format or other forms of request validation
   *     failure, such as invalid json. Usually serves application/json content, although in some
   *     cases simple text/plain content might be returned by the server&#39;s middleware. (status
   *     code 400) or Unauthorized. The request lacks valid authentication credentials for the
   *     operation. (status code 401) or Forbidden. Authenticated user does not have the necessary
   *     permissions. (status code 403) or A server-side problem that means can not find the
   *     specified resource. (status code 404) or The service is not ready to handle the request.
   *     The client should wait and retry. The service may additionally send a Retry-After header to
   *     indicate when to retry. (status code 503) or A server-side problem that might not be
   *     addressable from the client side. Used for server 5xx errors without more specific
   *     documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "deregisterTable",
      summary = "Deregister a table",
      description = "Deregister table `id` from its namespace. ",
      tags = {"Table", "Metadata"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Response of DeregisterTable",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DeregisterTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/deregister",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<DeregisterTableResponse> deregisterTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "DeregisterTableRequest", description = "", required = true)
          @Valid
          @RequestBody
          DeregisterTableRequest deregisterTableRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"location\" : \"location\", \"id\" : [ \"id\", \"id\" ], \"properties\" : { \"key\" : \"properties\" } }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/describe : Describe information of a table Describe the detailed
   * information for table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Table properties result when loading a table (status code 200) or Indicates a bad
   *     request error. It could be caused by an unexpected request body format or other forms of
   *     request validation failure, such as invalid json. Usually serves application/json content,
   *     although in some cases simple text/plain content might be returned by the server&#39;s
   *     middleware. (status code 400) or Unauthorized. The request lacks valid authentication
   *     credentials for the operation. (status code 401) or Forbidden. Authenticated user does not
   *     have the necessary permissions. (status code 403) or A server-side problem that means can
   *     not find the specified resource. (status code 404) or The service is not ready to handle
   *     the request. The client should wait and retry. The service may additionally send a
   *     Retry-After header to indicate when to retry. (status code 503) or A server-side problem
   *     that might not be addressable from the client side. Used for server 5xx errors without more
   *     specific documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "describeTable",
      summary = "Describe information of a table",
      description = "Describe the detailed information for table `id`. ",
      tags = {"Table", "Metadata"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Table properties result when loading a table",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DescribeTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/describe",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<DescribeTableResponse> describeTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "DescribeTableRequest", description = "", required = true)
          @Valid
          @RequestBody
          DescribeTableRequest describeTableRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"schema\" : { \"metadata\" : { \"key\" : \"metadata\" }, \"fields\" : [ { \"metadata\" : { \"key\" : \"metadata\" }, \"nullable\" : true, \"name\" : \"name\", \"type\" : { \"length\" : 0, \"fields\" : [ null, null ], \"type\" : \"type\" } }, { \"metadata\" : { \"key\" : \"metadata\" }, \"nullable\" : true, \"name\" : \"name\", \"type\" : { \"length\" : 0, \"fields\" : [ null, null ], \"type\" : \"type\" } } ] }, \"location\" : \"location\", \"version\" : 0, \"properties\" : { \"key\" : \"properties\" }, \"storage_options\" : { \"key\" : \"storage_options\" } }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/index/{index_name}/stats : Get table index statistics Get statistics for a
   * specific index on a table. Returns information about the index type, distance type (for vector
   * indices), and row counts.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to get stats for (required)
   * @param describeTableIndexStatsRequest Index stats request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Index statistics (status code 200) or Indicates a bad request error. It could be caused
   *     by an unexpected request body format or other forms of request validation failure, such as
   *     invalid json. Usually serves application/json content, although in some cases simple
   *     text/plain content might be returned by the server&#39;s middleware. (status code 400) or
   *     Unauthorized. The request lacks valid authentication credentials for the operation. (status
   *     code 401) or Forbidden. Authenticated user does not have the necessary permissions. (status
   *     code 403) or A server-side problem that means can not find the specified resource. (status
   *     code 404) or The service is not ready to handle the request. The client should wait and
   *     retry. The service may additionally send a Retry-After header to indicate when to retry.
   *     (status code 503) or A server-side problem that might not be addressable from the client
   *     side. Used for server 5xx errors without more specific documentation in individual routes.
   *     (status code 5XX)
   */
  @Operation(
      operationId = "describeTableIndexStats",
      summary = "Get table index statistics",
      description =
          "Get statistics for a specific index on a table. Returns information about the index type, distance type (for vector indices), and row counts. ",
      tags = {"Table", "Index", "Metadata"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Index statistics",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DescribeTableIndexStatsResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/index/{index_name}/stats",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<DescribeTableIndexStatsResponse> describeTableIndexStats(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(
              name = "index_name",
              description = "Name of the index to get stats for",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("index_name")
          String indexName,
      @Parameter(
              name = "DescribeTableIndexStatsRequest",
              description = "Index stats request",
              required = true)
          @Valid
          @RequestBody
          DescribeTableIndexStatsRequest describeTableIndexStatsRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"distance_type\" : \"distance_type\", \"num_unindexed_rows\" : 0, \"num_indexed_rows\" : 0, \"index_type\" : \"index_type\" }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/drop : Drop a table Drop table &#x60;id&#x60; and delete its data.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param dropTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Response of DropTable (status code 200) or Indicates a bad request error. It could be
   *     caused by an unexpected request body format or other forms of request validation failure,
   *     such as invalid json. Usually serves application/json content, although in some cases
   *     simple text/plain content might be returned by the server&#39;s middleware. (status code
   *     400) or Unauthorized. The request lacks valid authentication credentials for the operation.
   *     (status code 401) or Forbidden. Authenticated user does not have the necessary permissions.
   *     (status code 403) or A server-side problem that means can not find the specified resource.
   *     (status code 404) or The service is not ready to handle the request. The client should wait
   *     and retry. The service may additionally send a Retry-After header to indicate when to
   *     retry. (status code 503) or A server-side problem that might not be addressable from the
   *     client side. Used for server 5xx errors without more specific documentation in individual
   *     routes. (status code 5XX)
   */
  @Operation(
      operationId = "dropTable",
      summary = "Drop a table",
      description = "Drop table `id` and delete its data. ",
      tags = {"Table", "Metadata"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Response of DropTable",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DropTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/drop",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<DropTableResponse> dropTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "DropTableRequest", description = "", required = true) @Valid @RequestBody
          DropTableRequest dropTableRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"location\" : \"location\", \"id\" : [ \"id\", \"id\" ], \"properties\" : { \"key\" : \"properties\" }, \"transactionId\" : [ \"transactionId\", \"transactionId\" ] }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/insert : Insert records into a table Insert new records into table
   * &#x60;id&#x60;. REST NAMESPACE ONLY REST namespace uses Arrow IPC stream as the request body.
   * It passes in the &#x60;InsertIntoTableRequest&#x60; information in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;mode&#x60;: pass through
   * query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC stream containing the records to insert (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @param mode How the insert should behave: - append (default): insert data to the existing table
   *     - overwrite: remove all data in the table and then insert data to it (optional, default to
   *     append)
   * @return Result of inserting records into a table (status code 200) or Indicates a bad request
   *     error. It could be caused by an unexpected request body format or other forms of request
   *     validation failure, such as invalid json. Usually serves application/json content, although
   *     in some cases simple text/plain content might be returned by the server&#39;s middleware.
   *     (status code 400) or Unauthorized. The request lacks valid authentication credentials for
   *     the operation. (status code 401) or Forbidden. Authenticated user does not have the
   *     necessary permissions. (status code 403) or A server-side problem that means can not find
   *     the specified resource. (status code 404) or The service is not ready to handle the
   *     request. The client should wait and retry. The service may additionally send a Retry-After
   *     header to indicate when to retry. (status code 503) or A server-side problem that might not
   *     be addressable from the client side. Used for server 5xx errors without more specific
   *     documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "insertIntoTable",
      summary = "Insert records into a table",
      description =
          "Insert new records into table `id`.  REST NAMESPACE ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the `InsertIntoTableRequest` information in the following way: - `id`: pass through path parameter of the same name - `mode`: pass through query parameter of the same name ",
      tags = {"Table", "Data"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Result of inserting records into a table",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = InsertIntoTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/insert",
      produces = {"application/json"},
      consumes = {"application/vnd.apache.arrow.stream"})
  default ResponseEntity<InsertIntoTableResponse> insertIntoTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(
              name = "body",
              description = "Arrow IPC stream containing the records to insert",
              required = true)
          @Valid
          @RequestBody
          org.springframework.core.io.Resource body,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter,
      @Parameter(
              name = "mode",
              description =
                  "How the insert should behave: - append (default): insert data to the existing table - overwrite: remove all data in the table and then insert data to it ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "mode", required = false, defaultValue = "append")
          Optional<String> mode) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString = "{ \"version\" : 0 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/index/list : List indexes on a table List all indices created on a table.
   * Returns information about each index including name, columns, status, and UUID.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param listTableIndicesRequest Index list request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return List of indices on the table (status code 200) or Indicates a bad request error. It
   *     could be caused by an unexpected request body format or other forms of request validation
   *     failure, such as invalid json. Usually serves application/json content, although in some
   *     cases simple text/plain content might be returned by the server&#39;s middleware. (status
   *     code 400) or Unauthorized. The request lacks valid authentication credentials for the
   *     operation. (status code 401) or Forbidden. Authenticated user does not have the necessary
   *     permissions. (status code 403) or A server-side problem that means can not find the
   *     specified resource. (status code 404) or The service is not ready to handle the request.
   *     The client should wait and retry. The service may additionally send a Retry-After header to
   *     indicate when to retry. (status code 503) or A server-side problem that might not be
   *     addressable from the client side. Used for server 5xx errors without more specific
   *     documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "listTableIndices",
      summary = "List indexes on a table",
      description =
          "List all indices created on a table. Returns information about each index including name, columns, status, and UUID. ",
      tags = {"Table", "Index", "Metadata"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "List of indices on the table",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ListTableIndicesResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/index/list",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<ListTableIndicesResponse> listTableIndices(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(
              name = "ListTableIndicesRequest",
              description = "Index list request",
              required = true)
          @Valid
          @RequestBody
          ListTableIndicesRequest listTableIndicesRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"indexes\" : [ { \"index_uuid\" : \"index_uuid\", \"columns\" : [ \"columns\", \"columns\" ], \"index_name\" : \"index_name\", \"status\" : \"status\" }, { \"index_uuid\" : \"index_uuid\", \"columns\" : [ \"columns\", \"columns\" ], \"index_name\" : \"index_name\", \"status\" : \"status\" } ] }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/merge_insert : Merge insert (upsert) records into a table Performs a merge
   * insert (upsert) operation on table &#x60;id&#x60;. This operation updates existing rows based
   * on a matching column and inserts new rows that don&#39;t match. It returns the number of rows
   * inserted and updated. REST NAMESPACE ONLY REST namespace uses Arrow IPC stream as the request
   * body. It passes in the &#x60;MergeInsertIntoTableRequest&#x60; information in the following
   * way: - &#x60;id&#x60;: pass through path parameter of the same name - &#x60;on&#x60;: pass
   * through query parameter of the same name - &#x60;when_matched_update_all&#x60;: pass through
   * query parameter of the same name - &#x60;when_matched_update_all_filt&#x60;: pass through query
   * parameter of the same name - &#x60;when_not_matched_insert_all&#x60;: pass through query
   * parameter of the same name - &#x60;when_not_matched_by_source_delete&#x60;: pass through query
   * parameter of the same name - &#x60;when_not_matched_by_source_delete_filt&#x60;: pass through
   * query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param on Column name to use for matching rows (required) (required)
   * @param body Arrow IPC stream containing the records to merge (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @param whenMatchedUpdateAll Update all columns when rows match (optional, default to false)
   * @param whenMatchedUpdateAllFilt The row is updated (similar to UpdateAll) only for rows where
   *     the SQL expression evaluates to true (optional)
   * @param whenNotMatchedInsertAll Insert all columns when rows don&#39;t match (optional, default
   *     to false)
   * @param whenNotMatchedBySourceDelete Delete all rows from target table that don&#39;t match a
   *     row in the source table (optional, default to false)
   * @param whenNotMatchedBySourceDeleteFilt Delete rows from the target table if there is no match
   *     AND the SQL expression evaluates to true (optional)
   * @return Result of merge insert operation (status code 200) or Indicates a bad request error. It
   *     could be caused by an unexpected request body format or other forms of request validation
   *     failure, such as invalid json. Usually serves application/json content, although in some
   *     cases simple text/plain content might be returned by the server&#39;s middleware. (status
   *     code 400) or Unauthorized. The request lacks valid authentication credentials for the
   *     operation. (status code 401) or Forbidden. Authenticated user does not have the necessary
   *     permissions. (status code 403) or A server-side problem that means can not find the
   *     specified resource. (status code 404) or The service is not ready to handle the request.
   *     The client should wait and retry. The service may additionally send a Retry-After header to
   *     indicate when to retry. (status code 503) or A server-side problem that might not be
   *     addressable from the client side. Used for server 5xx errors without more specific
   *     documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "mergeInsertIntoTable",
      summary = "Merge insert (upsert) records into a table",
      description =
          "Performs a merge insert (upsert) operation on table `id`. This operation updates existing rows based on a matching column and inserts new rows that don't match. It returns the number of rows inserted and updated.  REST NAMESPACE ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the `MergeInsertIntoTableRequest` information in the following way: - `id`: pass through path parameter of the same name - `on`: pass through query parameter of the same name - `when_matched_update_all`: pass through query parameter of the same name - `when_matched_update_all_filt`: pass through query parameter of the same name - `when_not_matched_insert_all`: pass through query parameter of the same name - `when_not_matched_by_source_delete`: pass through query parameter of the same name - `when_not_matched_by_source_delete_filt`: pass through query parameter of the same name ",
      tags = {"Table", "Data"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Result of merge insert operation",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = MergeInsertIntoTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/merge_insert",
      produces = {"application/json"},
      consumes = {"application/vnd.apache.arrow.stream"})
  default ResponseEntity<MergeInsertIntoTableResponse> mergeInsertIntoTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @NotNull
          @Parameter(
              name = "on",
              description = "Column name to use for matching rows (required)",
              required = true,
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "on", required = true)
          String on,
      @Parameter(
              name = "body",
              description = "Arrow IPC stream containing the records to merge",
              required = true)
          @Valid
          @RequestBody
          org.springframework.core.io.Resource body,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter,
      @Parameter(
              name = "when_matched_update_all",
              description = "Update all columns when rows match",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "when_matched_update_all", required = false, defaultValue = "false")
          Optional<Boolean> whenMatchedUpdateAll,
      @Parameter(
              name = "when_matched_update_all_filt",
              description =
                  "The row is updated (similar to UpdateAll) only for rows where the SQL expression evaluates to true",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "when_matched_update_all_filt", required = false)
          Optional<String> whenMatchedUpdateAllFilt,
      @Parameter(
              name = "when_not_matched_insert_all",
              description = "Insert all columns when rows don't match",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(
              value = "when_not_matched_insert_all",
              required = false,
              defaultValue = "false")
          Optional<Boolean> whenNotMatchedInsertAll,
      @Parameter(
              name = "when_not_matched_by_source_delete",
              description =
                  "Delete all rows from target table that don't match a row in the source table",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(
              value = "when_not_matched_by_source_delete",
              required = false,
              defaultValue = "false")
          Optional<Boolean> whenNotMatchedBySourceDelete,
      @Parameter(
              name = "when_not_matched_by_source_delete_filt",
              description =
                  "Delete rows from the target table if there is no match AND the SQL expression evaluates to true",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "when_not_matched_by_source_delete_filt", required = false)
          Optional<String> whenNotMatchedBySourceDeleteFilt) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"num_inserted_rows\" : 0, \"num_updated_rows\" : 0, \"num_deleted_rows\" : 0, \"version\" : 0 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/query : Query a table Query table &#x60;id&#x60; with vector search, full
   * text search and optional SQL filtering. Returns results in Arrow IPC file or stream format.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param queryTableRequest Query request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Query results in Arrow IPC file or stream format (status code 200) or Indicates a bad
   *     request error. It could be caused by an unexpected request body format or other forms of
   *     request validation failure, such as invalid json. Usually serves application/json content,
   *     although in some cases simple text/plain content might be returned by the server&#39;s
   *     middleware. (status code 400) or Unauthorized. The request lacks valid authentication
   *     credentials for the operation. (status code 401) or Forbidden. Authenticated user does not
   *     have the necessary permissions. (status code 403) or A server-side problem that means can
   *     not find the specified resource. (status code 404) or The service is not ready to handle
   *     the request. The client should wait and retry. The service may additionally send a
   *     Retry-After header to indicate when to retry. (status code 503) or A server-side problem
   *     that might not be addressable from the client side. Used for server 5xx errors without more
   *     specific documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "queryTable",
      summary = "Query a table",
      description =
          "Query table `id` with vector search, full text search and optional SQL filtering. Returns results in Arrow IPC file or stream format. ",
      tags = {"Table", "Data"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Query results in Arrow IPC file or stream format",
            content = {
              @Content(
                  mediaType = "application/vnd.apache.arrow.file",
                  schema = @Schema(implementation = org.springframework.core.io.Resource.class)),
              @Content(
                  mediaType = "application/vnd.apache.arrow.stream",
                  schema = @Schema(implementation = org.springframework.core.io.Resource.class)),
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = org.springframework.core.io.Resource.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/vnd.apache.arrow.file",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/vnd.apache.arrow.stream",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/vnd.apache.arrow.file",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/vnd.apache.arrow.stream",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/vnd.apache.arrow.file",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/vnd.apache.arrow.stream",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/vnd.apache.arrow.file",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/vnd.apache.arrow.stream",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/vnd.apache.arrow.file",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/vnd.apache.arrow.stream",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/vnd.apache.arrow.file",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/vnd.apache.arrow.stream",
                  schema = @Schema(implementation = ErrorResponse.class)),
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/query",
      produces = {
        "application/vnd.apache.arrow.file",
        "application/vnd.apache.arrow.stream",
        "application/json"
      },
      consumes = {"application/json"})
  default ResponseEntity<org.springframework.core.io.Resource> queryTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "QueryTableRequest", description = "Query request", required = true)
          @Valid
          @RequestBody
          QueryTableRequest queryTableRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/register : Register a table to a namespace Register an existing table at a
   * given storage location as &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param registerTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Table properties result when registering a table (status code 200) or Indicates a bad
   *     request error. It could be caused by an unexpected request body format or other forms of
   *     request validation failure, such as invalid json. Usually serves application/json content,
   *     although in some cases simple text/plain content might be returned by the server&#39;s
   *     middleware. (status code 400) or Unauthorized. The request lacks valid authentication
   *     credentials for the operation. (status code 401) or Forbidden. Authenticated user does not
   *     have the necessary permissions. (status code 403) or A server-side problem that means can
   *     not find the specified resource. (status code 404) or Not Acceptable / Unsupported
   *     Operation. The server does not support this operation. (status code 406) or The request
   *     conflicts with the current state of the target resource. (status code 409) or The service
   *     is not ready to handle the request. The client should wait and retry. The service may
   *     additionally send a Retry-After header to indicate when to retry. (status code 503) or A
   *     server-side problem that might not be addressable from the client side. Used for server 5xx
   *     errors without more specific documentation in individual routes. (status code 5XX)
   */
  @Operation(
      operationId = "registerTable",
      summary = "Register a table to a namespace",
      description = "Register an existing table at a given storage location as `id`. ",
      tags = {"Table", "Metadata"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Table properties result when registering a table",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = RegisterTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "406",
            description =
                "Not Acceptable / Unsupported Operation. The server does not support this operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "409",
            description = "The request conflicts with the current state of the target resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/register",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<RegisterTableResponse> registerTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "RegisterTableRequest", description = "", required = true)
          @Valid
          @RequestBody
          RegisterTableRequest registerTableRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"location\" : \"location\", \"properties\" : { \"key\" : \"properties\" } }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/exists : Check if a table exists Check if table &#x60;id&#x60; exists. This
   * operation should behave exactly like DescribeTable, except it does not contain a response body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param tableExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Success, no content (status code 200) or Indicates a bad request error. It could be
   *     caused by an unexpected request body format or other forms of request validation failure,
   *     such as invalid json. Usually serves application/json content, although in some cases
   *     simple text/plain content might be returned by the server&#39;s middleware. (status code
   *     400) or Unauthorized. The request lacks valid authentication credentials for the operation.
   *     (status code 401) or Forbidden. Authenticated user does not have the necessary permissions.
   *     (status code 403) or A server-side problem that means can not find the specified resource.
   *     (status code 404) or The service is not ready to handle the request. The client should wait
   *     and retry. The service may additionally send a Retry-After header to indicate when to
   *     retry. (status code 503) or A server-side problem that might not be addressable from the
   *     client side. Used for server 5xx errors without more specific documentation in individual
   *     routes. (status code 5XX)
   */
  @Operation(
      operationId = "tableExists",
      summary = "Check if a table exists",
      description =
          "Check if table `id` exists.  This operation should behave exactly like DescribeTable,  except it does not contain a response body. ",
      tags = {"Table", "Metadata"},
      responses = {
        @ApiResponse(responseCode = "200", description = "Success, no content"),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/exists",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<Void> tableExists(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "TableExistsRequest", description = "", required = true) @Valid @RequestBody
          TableExistsRequest tableExistsRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /v1/table/{id}/update : Update rows in a table Update existing rows in table
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/./list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableRequest Update request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;.&#x60; delimiter must be used.
   *     (optional)
   * @return Update successful (status code 200) or Indicates a bad request error. It could be
   *     caused by an unexpected request body format or other forms of request validation failure,
   *     such as invalid json. Usually serves application/json content, although in some cases
   *     simple text/plain content might be returned by the server&#39;s middleware. (status code
   *     400) or Unauthorized. The request lacks valid authentication credentials for the operation.
   *     (status code 401) or Forbidden. Authenticated user does not have the necessary permissions.
   *     (status code 403) or A server-side problem that means can not find the specified resource.
   *     (status code 404) or The service is not ready to handle the request. The client should wait
   *     and retry. The service may additionally send a Retry-After header to indicate when to
   *     retry. (status code 503) or A server-side problem that might not be addressable from the
   *     client side. Used for server 5xx errors without more specific documentation in individual
   *     routes. (status code 5XX)
   */
  @Operation(
      operationId = "updateTable",
      summary = "Update rows in a table",
      description = "Update existing rows in table `id`. ",
      tags = {"Table", "Data"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Update successful",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UpdateTableResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "Indicates a bad request error. It could be caused by an unexpected request body format or other forms of request validation failure, such as invalid json. Usually serves application/json content, although in some cases simple text/plain content might be returned by the server's middleware.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description =
                "Unauthorized. The request lacks valid authentication credentials for the operation.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden. Authenticated user does not have the necessary permissions.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "A server-side problem that means can not find the specified resource.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description =
                "The service is not ready to handle the request. The client should wait and retry. The service may additionally send a Retry-After header to indicate when to retry.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "5XX",
            description =
                "A server-side problem that might not be addressable from the client side. Used for server 5xx errors without more specific documentation in individual routes.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/table/{id}/update",
      produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<UpdateTableResponse> updateTable(
      @Parameter(
              name = "id",
              description =
                  "`string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/./list` performs a `ListNamespace` on the root namespace. ",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          String id,
      @Parameter(name = "UpdateTableRequest", description = "Update request", required = true)
          @Valid
          @RequestBody
          UpdateTableRequest updateTableRequest,
      @Parameter(
              name = "delimiter",
              description =
                  "An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `.` delimiter must be used. ",
              in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "delimiter", required = false)
          Optional<String> delimiter) {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString = "{ \"updated_rows\" : 0, \"version\" : 0 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"instance\" : \"/login/log/abc123\", \"detail\" : \"Authentication failed due to incorrect username or password\", \"type\" : \"/errors/incorrect-user-pass\", \"title\" : \"Incorrect username or password\", \"status\" : 404 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
