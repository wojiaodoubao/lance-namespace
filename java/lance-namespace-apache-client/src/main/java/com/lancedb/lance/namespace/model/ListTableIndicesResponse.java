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
package com.lancedb.lance.namespace.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/** ListTableIndicesResponse */
@JsonPropertyOrder({ListTableIndicesResponse.JSON_PROPERTY_INDEXES})
@javax.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    comments = "Generator version: 7.12.0")
public class ListTableIndicesResponse {
  public static final String JSON_PROPERTY_INDEXES = "indexes";
  @javax.annotation.Nonnull private List<IndexListItemResponse> indexes = new ArrayList<>();

  public ListTableIndicesResponse() {}

  public ListTableIndicesResponse indexes(
      @javax.annotation.Nonnull List<IndexListItemResponse> indexes) {

    this.indexes = indexes;
    return this;
  }

  public ListTableIndicesResponse addIndexesItem(IndexListItemResponse indexesItem) {
    if (this.indexes == null) {
      this.indexes = new ArrayList<>();
    }
    this.indexes.add(indexesItem);
    return this;
  }

  /**
   * List of indexes on the table
   *
   * @return indexes
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_INDEXES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public List<IndexListItemResponse> getIndexes() {
    return indexes;
  }

  @JsonProperty(JSON_PROPERTY_INDEXES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setIndexes(@javax.annotation.Nonnull List<IndexListItemResponse> indexes) {
    this.indexes = indexes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListTableIndicesResponse listTableIndicesResponse = (ListTableIndicesResponse) o;
    return Objects.equals(this.indexes, listTableIndicesResponse.indexes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(indexes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListTableIndicesResponse {\n");
    sb.append("    indexes: ").append(toIndentedString(indexes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  /**
   * Convert the instance into URL query string.
   *
   * @return URL query string
   */
  public String toUrlQueryString() {
    return toUrlQueryString(null);
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    String suffix = "";
    String containerSuffix = "";
    String containerPrefix = "";
    if (prefix == null) {
      // style=form, explode=true, e.g. /pet?name=cat&type=manx
      prefix = "";
    } else {
      // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
      prefix = prefix + "[";
      suffix = "]";
      containerSuffix = "]";
      containerPrefix = "[";
    }

    StringJoiner joiner = new StringJoiner("&");

    // add `indexes` to the URL query string
    if (getIndexes() != null) {
      for (int i = 0; i < getIndexes().size(); i++) {
        if (getIndexes().get(i) != null) {
          joiner.add(
              getIndexes()
                  .get(i)
                  .toUrlQueryString(
                      String.format(
                          "%sindexes%s%s",
                          prefix,
                          suffix,
                          "".equals(suffix)
                              ? ""
                              : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    return joiner.toString();
  }
}
