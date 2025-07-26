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

import com.lancedb.lance.namespace.ObjectIdentifier;
import com.lancedb.lance.namespace.model.CreateNamespaceRequest;

import org.apache.hadoop.conf.Configuration;

import java.util.List;
import java.util.Map;

public interface HiveAdapter {
  static HiveAdapter create(HiveClientPool clientPool, Configuration hadoopConf) {
    switch (HiveVersion.version()) {
      case V2:
        return new Hive2Adapter(clientPool, hadoopConf);
      case V3:
        return new Hive3Adapter(clientPool, hadoopConf);
    }

    throw new UnsupportedOperationException(
        String.format("Unsupported hive version %s", HiveVersion.version()));
  }

  List<String> listNamespaces(ObjectIdentifier parent);

  void createNamespace(
      ObjectIdentifier id, CreateNamespaceRequest.ModeEnum mode, Map<String, String> properties);
}
