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
package com.lancedb.lance.namespace;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectIdentifier {
  private String[] levels;

  private ObjectIdentifier(String[] levels) {
    this.levels = levels;
  }

  public static ObjectIdentifier of(List<String> levels) {
    List<String> normalizedLevels =
        levels.stream()
            .filter(level -> level != null && !level.isEmpty())
            .collect(Collectors.toList());
    return new ObjectIdentifier(normalizedLevels.toArray(new String[0]));
  }

  public String level(int pos) {
    return levels[pos];
  }

  public String name() {
    return empty() ? "" : levels[levels.length - 1];
  }

  public List<String> parent() {
    return size() < 2
        ? ImmutableList.of()
        : ImmutableList.copyOf(Arrays.copyOfRange(levels, 0, levels.length - 1));
  }

  public int size() {
    return levels == null ? 0 : levels.length;
  }

  public boolean empty() {
    return size() == 0;
  }
}
