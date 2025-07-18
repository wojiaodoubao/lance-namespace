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

import com.lancedb.lance.namespace.Configurable;
import com.lancedb.lance.namespace.HiveVersion;
import com.lancedb.lance.namespace.LanceNamespace;
import com.lancedb.lance.namespace.ObjectIdentifier;
import com.lancedb.lance.namespace.model.AlterTransactionRequest;
import com.lancedb.lance.namespace.model.AlterTransactionResponse;
import com.lancedb.lance.namespace.model.CountRowsRequest;
import com.lancedb.lance.namespace.model.CreateIndexRequest;
import com.lancedb.lance.namespace.model.CreateIndexResponse;
import com.lancedb.lance.namespace.model.CreateNamespaceRequest;
import com.lancedb.lance.namespace.model.CreateNamespaceResponse;
import com.lancedb.lance.namespace.model.CreateTableResponse;
import com.lancedb.lance.namespace.model.DeleteFromTableRequest;
import com.lancedb.lance.namespace.model.DeleteFromTableResponse;
import com.lancedb.lance.namespace.model.DeregisterTableRequest;
import com.lancedb.lance.namespace.model.DeregisterTableResponse;
import com.lancedb.lance.namespace.model.DescribeNamespaceRequest;
import com.lancedb.lance.namespace.model.DescribeNamespaceResponse;
import com.lancedb.lance.namespace.model.DescribeTableRequest;
import com.lancedb.lance.namespace.model.DescribeTableResponse;
import com.lancedb.lance.namespace.model.DescribeTransactionRequest;
import com.lancedb.lance.namespace.model.DescribeTransactionResponse;
import com.lancedb.lance.namespace.model.DropNamespaceRequest;
import com.lancedb.lance.namespace.model.DropNamespaceResponse;
import com.lancedb.lance.namespace.model.DropTableRequest;
import com.lancedb.lance.namespace.model.DropTableResponse;
import com.lancedb.lance.namespace.model.IndexListRequest;
import com.lancedb.lance.namespace.model.IndexListResponse;
import com.lancedb.lance.namespace.model.IndexStatsRequest;
import com.lancedb.lance.namespace.model.IndexStatsResponse;
import com.lancedb.lance.namespace.model.InsertTableResponse;
import com.lancedb.lance.namespace.model.ListNamespacesRequest;
import com.lancedb.lance.namespace.model.ListNamespacesResponse;
import com.lancedb.lance.namespace.model.MergeInsertTableRequest;
import com.lancedb.lance.namespace.model.MergeInsertTableResponse;
import com.lancedb.lance.namespace.model.NamespaceExistsRequest;
import com.lancedb.lance.namespace.model.NamespaceExistsResponse;
import com.lancedb.lance.namespace.model.QueryRequest;
import com.lancedb.lance.namespace.model.RegisterTableRequest;
import com.lancedb.lance.namespace.model.RegisterTableResponse;
import com.lancedb.lance.namespace.model.TableExistsRequest;
import com.lancedb.lance.namespace.model.TableExistsResponse;
import com.lancedb.lance.namespace.model.UpdateTableRequest;
import com.lancedb.lance.namespace.model.UpdateTableResponse;
import com.lancedb.lance.namespace.util.PageUtil;
import com.lancedb.lance.namespace.util.ValidationUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.hadoop.conf.Configuration;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.lancedb.lance.namespace.HiveNamespaceProperties.HIVE_CLIENT_POOL_SIZE;
import static com.lancedb.lance.namespace.HiveNamespaceProperties.HIVE_CLIENT_POOL_SIZE_DEFAULT;

public class LanceHiveNamespace implements LanceNamespace, Configurable<Configuration> {
  private static final Logger LOG = LoggerFactory.getLogger(LanceHiveNamespace.class);

  private HiveClientPool clientPool;
  private String name;
  private Map<String, String> properties;
  private Configuration hadoopConf;

  public LanceHiveNamespace() {}

  @Override
  public void initialize(String name, Map<String, String> properties) {
    if (hadoopConf == null) {
      LOG.warn("Hadoop configuration not set, using the default configuration.");
      hadoopConf = new Configuration();
    }

    this.name = name;
    this.properties = properties;

    int poolSize =
        Integer.parseInt(
            properties.getOrDefault(HIVE_CLIENT_POOL_SIZE, HIVE_CLIENT_POOL_SIZE_DEFAULT));

    this.clientPool = new HiveClientPool(poolSize, hadoopConf);
  }

  @Override
  public ListNamespacesResponse listNamespaces(ListNamespacesRequest request) {
    ObjectIdentifier parent = ObjectIdentifier.of(request.getParent());

    List<String> nss = null;
    switch (HiveVersion.version()) {
      case V2:
        nss = listNamespacesV2(parent);
        break;
      case V3:
        nss = listNamespacesV3(parent);
        break;
    }

    Collections.sort(nss);
    PageUtil.Page page =
        PageUtil.splitPage(
            nss, request.getPageToken(), PageUtil.normalizePageSize(request.getPageSize()));

    ListNamespacesResponse response = new ListNamespacesResponse();
    response.setNamespaces(Sets.newHashSet(page.items()));
    response.setNextPageToken(page.nextPageToken());
    return response;
  }

  private List<String> listNamespacesV2(ObjectIdentifier parent) {
    ValidationUtil.checkArgument(
        parent.size() <= 2, "Expect a 2-level namespace but get %s", parent);

    try {
      if (parent.empty()) {
        return clientPool.run(client -> client.getAllDatabases());
      } else {
        return Lists.newArrayList();
      }
    } catch (TException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }

  private List<String> listNamespacesV3(ObjectIdentifier parent) {
    ValidationUtil.checkArgument(
        parent.size() <= 3, "Expect a 3-level namespace but get %s", parent);

    try {
      if (parent.empty()) {
        return clientPool.run(client -> client.getCatalogs());
      } else if (parent.size() == 1) {
        return clientPool.run(client -> client.getAllDatabases(parent.level(0)));
      } else {
        return Lists.newArrayList();
      }
    } catch (TException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DescribeNamespaceResponse describeNamespace(DescribeNamespaceRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public CreateNamespaceResponse createNamespace(CreateNamespaceRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public DropNamespaceResponse dropNamespace(DropNamespaceRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public NamespaceExistsResponse namespaceExists(NamespaceExistsRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public DescribeTableResponse describeTable(DescribeTableRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public Long countRows(CountRowsRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public CreateTableResponse createTable(String tableName, byte[] arrowIpcData) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public InsertTableResponse insertTable(String tableName, byte[] arrowIpcData, String mode) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public MergeInsertTableResponse mergeInsertTable(
      MergeInsertTableRequest request,
      byte[] arrowIpcData,
      String on,
      Boolean whenMatchedUpdateAll,
      Boolean whenNotMatchedInsertAll) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public UpdateTableResponse updateTable(UpdateTableRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public DeleteFromTableResponse deleteFromTable(DeleteFromTableRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public byte[] queryTable(QueryRequest request) {
    return new byte[0];
  }

  @Override
  public CreateIndexResponse createIndex(CreateIndexRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public CreateIndexResponse createScalarIndex(CreateIndexRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public IndexListResponse listIndices(IndexListRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public IndexStatsResponse getIndexStats(IndexStatsRequest request, String indexName) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public RegisterTableResponse registerTable(RegisterTableRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public TableExistsResponse tableExists(TableExistsRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public DropTableResponse dropTable(DropTableRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public DeregisterTableResponse deregisterTable(DeregisterTableRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public DescribeTransactionResponse describeTransaction(DescribeTransactionRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public AlterTransactionResponse alterTransaction(AlterTransactionRequest request) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public void setConf(Configuration conf) {
    this.hadoopConf = conf;
  }
}
