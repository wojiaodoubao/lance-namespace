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

import com.lancedb.lance.namespace.LanceNamespaceException;
import com.lancedb.lance.namespace.ObjectIdentifier;
import com.lancedb.lance.namespace.model.CreateNamespaceRequest;
import com.lancedb.lance.namespace.util.CommonUtil;
import com.lancedb.lance.namespace.util.HiveUtil;
import com.lancedb.lance.namespace.util.ValidationUtil;

import com.google.common.collect.Lists;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.thrift.TException;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.lancedb.lance.namespace.hive.ErrorType.DatabaseAlreadyExist;
import static com.lancedb.lance.namespace.hive.ErrorType.HiveMetaStoreError;

public class Hive2Adapter implements HiveAdapter {
  private HiveClientPool clientPool;
  private Configuration hadoopConf;

  public Hive2Adapter(HiveClientPool clientPool, Configuration hadoopConf) {
    this.clientPool = clientPool;
    this.hadoopConf = hadoopConf;
  }

  @Override
  public List<String> listNamespaces(ObjectIdentifier parent) {
    ValidationUtil.checkArgument(
        parent.levels() <= 2, "Expect a 2-level namespace but get %s", parent);

    try {
      if (parent.isRoot()) {
        return clientPool.run(IMetaStoreClient::getAllDatabases);
      } else {
        return Lists.newArrayList();
      }
    } catch (TException | InterruptedException e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      throw LanceNamespaceException.serviceUnavailable(
          e.getMessage(), HiveMetaStoreError.getType(), "", CommonUtil.formatCurrentStackTrace());
    }
  }

  @Override
  public void createNamespace(
      ObjectIdentifier id, CreateNamespaceRequest.ModeEnum mode, Map<String, String> properties) {
    ValidationUtil.checkArgument(id.levels() == 2, "Expect a 2-level namespace but get %s", id);

    try {
      String db = id.level(0).toLowerCase();
      createDatabase(db, mode, properties);
    } catch (TException | InterruptedException e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      throw LanceNamespaceException.serviceUnavailable(
          e.getMessage(), HiveMetaStoreError.getType(), "", CommonUtil.formatCurrentStackTrace());
    }
  }

  private void createDatabase(
      String dbName, CreateNamespaceRequest.ModeEnum mode, Map<String, String> properties)
      throws TException, InterruptedException {
    Database oldDb = HiveUtil.getDatabaseOrNull(clientPool, dbName);
    if (oldDb != null) {
      switch (mode) {
        case CREATE:
          throw LanceNamespaceException.conflict(
              String.format("Database %s already exist", dbName),
              DatabaseAlreadyExist.getType(),
              "",
              CommonUtil.formatCurrentStackTrace());
        case EXIST_OK:
          return;
        case OVERWRITE:
          clientPool.run(
              client -> {
                client.dropDatabase(dbName, false, true, false);
                return null;
              });
      }
    }

    // Create database
    Supplier<String> warehouseLocation =
        () ->
            ValidationUtil.checkNotNullOrEmptyString(
                hadoopConf.get(HiveConf.ConfVars.METASTOREWAREHOUSE.varname),
                String.format(
                    "Warehouse location is not set: %s=null",
                    HiveConf.ConfVars.METASTOREWAREHOUSE.varname));

    Database database = new Database();
    database.setName(dbName);
    HiveUtil.setDatabaseProperties(database, warehouseLocation, dbName, properties);

    clientPool.run(
        client -> {
          client.createDatabase(database);
          return null;
        });
  }
}
