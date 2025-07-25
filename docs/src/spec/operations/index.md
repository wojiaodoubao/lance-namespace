# Namespace Operations

The Lance Namespace Specification defines a list of operations that can be performed against any Lance namespace:

| Operation ID                                             | Current Version | Namespace | Table | Index | Metadata | Data | Transaction |
|----------------------------------------------------------|-----------------|-----------|-------|-------|----------|------|-------------|
| [CreateNamespace](create-namespace.md)                   | 1               | ✓         |       |       | ✓        |      |             |
| [ListNamespaces](list-namespaces.md)                     | 1               | ✓         |       |       | ✓        |      |             |
| [DescribeNamespace](describe-namespace.md)               | 1               | ✓         |       |       | ✓        |      |             |
| [DropNamespace](drop-namespace.md)                       | 1               | ✓         |       |       | ✓        |      |             |
| [NamespaceExists](namespace-exists.md)                   | 1               | ✓         |       |       | ✓        |      |             |
| [ListTables](list-tables.md)                             | 1               | ✓         | ✓     |       | ✓        |      |             |
| [RegisterTable](register-table.md)                       | 1               |           | ✓     |       | ✓        |      |             |
| [DescribeTable](describe-table.md)                       | 1               |           | ✓     |       | ✓        |      |             |
| [TableExists](table-exists.md)                           | 1               |           | ✓     |       | ✓        |      |             |
| [DropTable](drop-table.md)                               | 1               |           | ✓     |       | ✓        |      |             |
| [DeregisterTable](deregister-table.md)                   | 1               |           | ✓     |       | ✓        |      |             |
| [InsertIntoTable](insert-into-table.md)                  | 1               |           | ✓     |       |          | ✓    |             |
| [MergeInsertIntoTable](merge-insert-into-table.md)       | 1               |           | ✓     |       |          | ✓    |             |
| [UpdateTable](update-table.md)                           | 1               |           | ✓     |       |          | ✓    |             |
| [DeleteFromTable](delete-from-table.md)                  | 1               |           | ✓     |       |          | ✓    |             |
| [QueryTable](query-table.md)                             | 1               |           | ✓     |       |          | ✓    |             |
| [CountTableRows](count-table-rows.md)                    | 1               |           | ✓     |       |          | ✓    |             |
| [CreateTable](create-table.md)                           | 1               |           | ✓     |       |          | ✓    |             |
| [CreateTableIndex](create-table-index.md)                | 1               |           | ✓     | ✓     | ✓        |      |             |
| [ListTableIndices](list-table-indices.md)                | 1               |           | ✓     | ✓     | ✓        |      |             |
| [DescribeTableIndexStats](describe-table-index-stats.md) | 1               |           | ✓     | ✓     | ✓        |      |             |
| [DescribeTransaction](describe-transaction.md)           | 1               |           |       |       | ✓        |      | ✓           |
| [AlterTransaction](alter-transaction.md)                 | 1               |           |       |       | ✓        |      | ✓           |

## Operation Versioning

When backwards incompatible change is introduced,
a new operation version needs to be created, with a naming convention of `<operationId>V<version>`,
for example `ListNamespacesV2`, `DescribeTableV3`, etc.

## Operation Request and Response Schema

In general, each operation has a request and response.
The request and response schema is defined using JSON schema in the 
`components/schemas` section of the [OpenAPI specification](https://editor-next.swagger.io/?url=https://raw.githubusercontent.com/lancedb/lance-namespace/refs/heads/main/docs/src/spec/rest.yaml).

!!! note
    For exceptions to this rule, see the Notes section of the operations

## Error Response Model

All error responses follow the JSON error response model based on [RFC-7807](https://datatracker.ietf.org/doc/html/rfc7807):

```yaml
--8<-- "src/spec/rest.yaml:1061:1089"
```

## HTTP Status Codes and Responses

### 400 - Bad Request Error Response

```yaml
--8<-- "src/spec/rest.yaml:2414:2431"
```

### 401 - Unauthorized Error Response

```yaml
--8<-- "src/spec/rest.yaml:2432:2445"
```

### 403 - Forbidden Error Response

```yaml
--8<-- "src/spec/rest.yaml:2446:2459"
```

### 404 - Not Found Error Response

```yaml
--8<-- "src/spec/rest.yaml:2460:2474"
```

### 406 - Unsupported Operation Error Response

```yaml
--8<-- "src/spec/rest.yaml:2475:2488"
```

### 409 - Conflict Error Response

```yaml
--8<-- "src/spec/rest.yaml:2489:2502"
```

### 503 - Service Unavailable Error Response

```yaml
--8<-- "src/spec/rest.yaml:2503:2518"
```

### 5XX - Server Error Response

```yaml
--8<-- "src/spec/rest.yaml:2519:2535"
```