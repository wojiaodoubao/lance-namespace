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
package com.lancedb.lance.namespace.conf;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.hadoop.conf.Configuration;

import java.util.List;

public class ConfKey<T> {
  private final Parser<T> parser;
  private final String key;
  private final T defaultValue;
  private final String doc;
  private final String version;

  private ConfKey(Parser<T> parser, String key, T defaultValue, String doc, String version) {
    this.parser = parser;
    this.key = key;
    this.defaultValue = defaultValue;
    this.doc = doc;
    this.version = version;
  }

  public String key() {
    return key;
  }

  public T defaultValue() {
    return defaultValue;
  }

  public String doc() {
    return doc;
  }

  public String version() {
    return version;
  }

  public T readValue(Configuration conf) {
    return parser.parse(conf, key, defaultValue);
  }

  interface Parser<T> {
    T parse(Configuration conf, String key, T defaultValue);
  }

  public static Builder<Integer> intType() {
    return Builder.of((conf, key, defaultValue) -> conf.getInt(key, defaultValue), true);
  }

  public static Builder<Long> longType() {
    return Builder.of((conf, key, defaultValue) -> conf.getLong(key, defaultValue), true);
  }

  public static Builder<Double> doubleType() {
    return Builder.of((conf, key, defaultValue) -> conf.getDouble(key, defaultValue), true);
  }

  public static Builder<Boolean> booleanType() {
    return Builder.of((conf, key, defaultValue) -> conf.getBoolean(key, defaultValue), true);
  }

  public static Builder<String> stringType() {
    return Builder.of((conf, key, defaultValue) -> conf.get(key, defaultValue), false);
  }

  public static Builder<List<String>> listType() {
    return Builder.of(ConfKey::getList, false);
  }

  private static List<String> getList(Configuration conf, String key, List<String> defaultValue) {
    List<String> value = Lists.newArrayList(conf.getStringCollection(key));
    if (value == null) {
      return defaultValue;
    } else {
      return value;
    }
  }

  public static class Builder<T> {
    private final Parser<T> parser;
    private final boolean isPrimitiveType;

    private String key;
    private T defaultValue = null;
    private String doc;
    private String version;

    private Builder(Parser<T> parser, boolean isPrimitiveType) {
      this.parser = parser;
      this.isPrimitiveType = isPrimitiveType;
    }

    public static <T> Builder<T> of(Parser<T> parser, boolean isPrimitiveType) {
      return new Builder<>(parser, isPrimitiveType);
    }

    public Builder<T> key(String key) {
      this.key = key;
      return this;
    }

    public Builder<T> defaultValue(T defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    public Builder<T> doc(String doc) {
      this.doc = doc;
      return this;
    }

    public Builder<T> version(String version) {
      this.version = version;
      return this;
    }

    public ConfKey<T> build() {
      Preconditions.checkNotNull(key, "Conf key cannot be null");
      Preconditions.checkArgument(!key.contains("%s"), "ConfKey key should not contain any '%s'.");
      Preconditions.checkArgument(
          !isPrimitiveType || defaultValue != null,
          "Default value must be provided for primitive data type, key: %s",
          key);
      Preconditions.checkNotNull(doc, "Doc cannot be null, key: %s", key);
      Preconditions.checkNotNull(version, "Version cannot be null, key: %s", key);

      return new ConfKey<>(parser, key, defaultValue, doc, version);
    }
  }
}
