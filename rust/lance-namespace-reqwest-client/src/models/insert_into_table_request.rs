/*
 * Lance Namespace Specification
 *
 * This OpenAPI specification is a part of the Lance namespace specification. It contains 2 parts:  The `components/schemas`, `components/responses`, `components/examples`, `tags` sections define the request and response shape for each operation in a Lance Namespace across all implementations. See https://lancedb.github.io/lance-namespace/spec/operations for more details.  The `servers`, `security`, `paths`, `components/parameters` sections are for the  Lance REST Namespace implementation, which defines a complete REST server that can work with Lance datasets. See https://lancedb.github.io/lance-namespace/spec/impls/rest for more details. 
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 * Generated by: https://openapi-generator.tech
 */

use crate::models;
use serde::{Deserialize, Serialize};

/// InsertIntoTableRequest : Request for inserting records into a table, excluding the Arrow IPC stream. 
#[derive(Clone, Default, Debug, PartialEq, Serialize, Deserialize)]
pub struct InsertIntoTableRequest {
    #[serde(rename = "id", skip_serializing_if = "Option::is_none")]
    pub id: Option<Vec<String>>,
    #[serde(rename = "mode", skip_serializing_if = "Option::is_none")]
    pub mode: Option<Mode>,
}

impl InsertIntoTableRequest {
    /// Request for inserting records into a table, excluding the Arrow IPC stream. 
    pub fn new() -> InsertIntoTableRequest {
        InsertIntoTableRequest {
            id: None,
            mode: None,
        }
    }
}
/// 
#[derive(Clone, Copy, Debug, Eq, PartialEq, Ord, PartialOrd, Hash, Serialize, Deserialize)]
pub enum Mode {
    #[serde(rename = "append")]
    Append,
    #[serde(rename = "overwrite")]
    Overwrite,
}

impl Default for Mode {
    fn default() -> Mode {
        Self::Append
    }
}

