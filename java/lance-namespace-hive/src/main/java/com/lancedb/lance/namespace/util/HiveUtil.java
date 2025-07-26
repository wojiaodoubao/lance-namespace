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
package com.lancedb.lance.namespace.util;

import com.lancedb.lance.namespace.LanceNamespaceException;
import com.lancedb.lance.namespace.hive.HiveClientPool;
import com.lancedb.lance.namespace.hive.HiveNamespaceConfig;

import com.google.common.collect.Maps;
import org.apache.hadoop.hive.metastore.api.Catalog;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.thrift.TException;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

import static com.lancedb.lance.namespace.hive.ErrorType.HiveMetaStoreError;
import static com.lancedb.lance.namespace.hive.ErrorType.UnknownCatalog;

public class HiveUtil {
  public static Catalog getCatalogOrNull(HiveClientPool clientPool, String catalog) {
    try {
      return clientPool.run(client -> client.getCatalog(catalog));
    } catch (NoSuchObjectException e) {
      return null;
    } catch (TException | InterruptedException e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      throw LanceNamespaceException.serviceUnavailable(
          e.getMessage(), HiveMetaStoreError.getType(), "", CommonUtil.formatCurrentStackTrace());
    }
  }

  public static Catalog getCatalogOrThrowNotFoundException(
      HiveClientPool clientPool, String catalog) {
    Catalog catalogObj = getCatalogOrNull(clientPool, catalog);
    if (catalogObj == null) {
      throw LanceNamespaceException.notFound(
          String.format("Catalog %s doesn't exist", catalog),
          UnknownCatalog.getType(),
          "",
          CommonUtil.formatCurrentStackTrace());
    }
    return catalogObj;
  }

  public static Database getDatabaseOrNull(HiveClientPool clientPool, String catalog, String db) {
    try {
      return clientPool.run(client -> client.getDatabase(catalog, db));
    } catch (NoSuchObjectException e) {
      return null;
    } catch (TException | InterruptedException e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      throw LanceNamespaceException.serviceUnavailable(
          e.getMessage(), HiveMetaStoreError.getType(), "", CommonUtil.formatCurrentStackTrace());
    }
  }

  public static Database getDatabaseOrNull(HiveClientPool clientPool, String db) {
    try {
      return clientPool.run(client -> client.getDatabase(db));
    } catch (NoSuchObjectException e) {
      return null;
    } catch (TException | InterruptedException e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      throw LanceNamespaceException.serviceUnavailable(
          e.getMessage(), HiveMetaStoreError.getType(), "", CommonUtil.formatCurrentStackTrace());
    }
  }

  public static Table getTableOrNull(
      HiveClientPool clientPool, String catalog, String db, String table) {
    try {
      return clientPool.run(client -> client.getTable(catalog, db, table));
    } catch (NoSuchObjectException e) {
      return null;
    } catch (TException | InterruptedException e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      throw LanceNamespaceException.serviceUnavailable(
          e.getMessage(), HiveMetaStoreError.getType(), "", CommonUtil.formatCurrentStackTrace());
    }
  }

  public static void setDatabaseProperties(
      Database database,
      Supplier<String> warehouseLocation,
      String dbName,
      Map<String, String> properties) {
    Map<String, String> parameters = Maps.newHashMap();
    properties.forEach(
        (k, v) -> {
          if (k.equals(HiveNamespaceConfig.DATABASE_DESCRIPTION)) {
            database.setDescription(v);
          } else if (k.equals(HiveNamespaceConfig.DATABASE_LOCATION_URI)) {
            database.setLocationUri(v);
          } else if (k.equals(HiveNamespaceConfig.DATABASE_OWNER)) {
            database.setOwnerName(v);
          } else if (k.equals(HiveNamespaceConfig.DATABASE_OWNER_TYPE) && v != null) {
            database.setOwnerType(PrincipalType.valueOf(v));
          } else if (v != null) {
            parameters.put(k, v);
          }
        });

    if (!database.isSetLocationUri()) {
      String location = databaseLocation(warehouseLocation.get(), dbName);
      database.setLocationUri(location);
      properties.put(HiveNamespaceConfig.DATABASE_LOCATION_URI, location);
    }
    if (!database.isSetOwnerName()) {
      String user = hadoopUser();
      database.setOwnerName(user);
      properties.put(HiveNamespaceConfig.DATABASE_OWNER, user);
    }
    if (!database.isSetOwnerType()) {
      database.setOwnerType(PrincipalType.USER);
      properties.put(HiveNamespaceConfig.DATABASE_OWNER_TYPE, PrincipalType.USER.name());
    }

    database.setParameters(parameters);
  }

  public static String databaseLocation(String warehouse, String dbName) {
    if (!warehouse.endsWith("://")) {
      int end = warehouse.length();
      while (end > 0 && warehouse.charAt(end - 1) == '/') {
        end--;
      }
      warehouse = warehouse.substring(0, end);
    }

    return String.format("%s/%s", warehouse, dbName);
  }

  public static String hadoopUser() {
    try {
      return UserGroupInformation.getCurrentUser().getUserName();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
