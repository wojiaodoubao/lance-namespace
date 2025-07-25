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
package com.lancedb.lance.namespace.hive;

import com.lancedb.lance.namespace.util.PropertyUtil;

import java.util.Map;

public class HiveNamespaceConfig {

  public static final String CLIENT_POOL_SIZE = "client.pool-size";

  public static final int CLIENT_POOL_SIZE_DEFAULT = 3;

  private final int clientPoolSize;

  public HiveNamespaceConfig(Map<String, String> properties) {
    this.clientPoolSize =
        PropertyUtil.propertyAsInt(properties, CLIENT_POOL_SIZE, CLIENT_POOL_SIZE_DEFAULT);
  }

  public int getClientPoolSize() {
    return clientPoolSize;
  }
}
