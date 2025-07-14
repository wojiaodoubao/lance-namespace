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

import com.lancedb.lance.namespace.HiveVersion;
import com.lancedb.lance.namespace.LanceNamespace;
import com.lancedb.lance.namespace.ObjectIdentifier;
import com.lancedb.lance.namespace.conf.ConfKeys;
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
import com.lancedb.lance.namespace.util.PageUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.hadoop.conf.Configuration;
import org.apache.thrift.TException;

import java.util.Collections;
import java.util.List;

public class LanceHMSNamespace implements LanceNamespace {
  private HiveClientPool clientPool;

  public LanceHMSNamespace(Configuration conf) {
    int poolSize = ConfKeys.HMS_CLIENT_POOL_SIZE.readValue(conf);
    this.clientPool = new HiveClientPool(poolSize, conf);
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
    PageUtils.Page page =
        PageUtils.splitPage(
            nss, request.getPageToken(), PageUtils.normalizePageSize(request.getPageSize()));

    ListNamespacesResponse response = new ListNamespacesResponse();
    response.setNamespaces(Sets.newHashSet(page.items()));
    response.setNextPageToken(page.nextPageToken());
    return response;
  }

  private List<String> listNamespacesV2(ObjectIdentifier parent) {
    try {
      if (parent.empty()) {
        return clientPool.run(client -> client.getAllDatabases());
      } else if (parent.size() <= 2) {
        return Lists.newArrayList();
      } else {
        throw new RuntimeException(String.format("Namespace parent {} is too long.", parent));
      }
    } catch (TException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private List<String> listNamespacesV3(ObjectIdentifier parent) {
    try {
      if (parent.empty()) {
        return clientPool.run(client -> client.getCatalogs());
      } else if (parent.size() == 1) {
        return clientPool.run(client -> client.getAllDatabases(parent.level(0)));
      } else if (parent.size() <= 3) {
        return Lists.newArrayList();
      } else {
        throw new RuntimeException(String.format("Namespace parent {} is too long.", parent));
      }
    } catch (TException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DescribeNamespaceResponse describeNamespace(DescribeNamespaceRequest request) {
    return null;
  }

  @Override
  public CreateNamespaceResponse createNamespace(CreateNamespaceRequest request) {
    return null;
  }

  @Override
  public DropNamespaceResponse dropNamespace(DropNamespaceRequest request) {
    return null;
  }

  @Override
  public NamespaceExistsResponse namespaceExists(NamespaceExistsRequest request) {
    return null;
  }

  @Override
  public DescribeTableResponse describeTable(DescribeTableRequest request) {
    return null;
  }

  @Override
  public Long countRows(CountRowsRequest request) {
    return null;
  }

  @Override
  public CreateTableResponse createTable(String tableName, byte[] arrowIpcData) {
    return null;
  }

  @Override
  public InsertTableResponse insertTable(String tableName, byte[] arrowIpcData, String mode) {
    return null;
  }

  @Override
  public MergeInsertTableResponse mergeInsertTable(
      MergeInsertTableRequest request,
      byte[] arrowIpcData,
      String on,
      Boolean whenMatchedUpdateAll,
      Boolean whenNotMatchedInsertAll) {
    return null;
  }

  @Override
  public UpdateTableResponse updateTable(UpdateTableRequest request) {
    return null;
  }

  @Override
  public DeleteFromTableResponse deleteFromTable(DeleteFromTableRequest request) {
    return null;
  }

  @Override
  public byte[] queryTable(QueryRequest request) {
    return new byte[0];
  }

  @Override
  public CreateIndexResponse createIndex(CreateIndexRequest request) {
    return null;
  }

  @Override
  public CreateIndexResponse createScalarIndex(CreateIndexRequest request) {
    return null;
  }

  @Override
  public IndexListResponse listIndices(IndexListRequest request) {
    return null;
  }

  @Override
  public IndexStatsResponse getIndexStats(IndexStatsRequest request, String indexName) {
    return null;
  }

  @Override
  public RegisterTableResponse registerTable(RegisterTableRequest request) {
    return null;
  }

  @Override
  public TableExistsResponse tableExists(TableExistsRequest request) {
    return null;
  }

  @Override
  public DropTableResponse dropTable(DropTableRequest request) {
    return null;
  }

  @Override
  public DeregisterTableResponse deregisterTable(DeregisterTableRequest request) {
    return null;
  }

  @Override
  public DescribeTransactionResponse describeTransaction(DescribeTransactionRequest request) {
    return null;
  }

  @Override
  public AlterTransactionResponse alterTransaction(AlterTransactionRequest request) {
    return null;
  }
}
