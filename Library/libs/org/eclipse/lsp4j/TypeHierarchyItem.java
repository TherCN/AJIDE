/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import com.google.gson.annotations.JsonAdapter;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Representation of an item that carries type information.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class TypeHierarchyItem {
  /**
   * The name of this item.
   */
  @NonNull
  private String name;

  /**
   * More detail for this item, e.g. the signature of a function.
   */
  private String detail;

  /**
   * The kind of this item.
   */
  @NonNull
  private SymbolKind kind;

  /**
   * Tags for this item.
   */
  private List<SymbolTag> tags;

  /**
   * The resource identifier of this item.
   */
  @NonNull
  private String uri;

  /**
   * The range enclosing this symbol not including leading/trailing whitespace
   * but everything else, e.g. comments and code.
   */
  @NonNull
  private Range range;

  /**
   * The range that should be selected and revealed when this symbol is being
   * picked, e.g. the name of a function. Must be contained by the {@link #range}.
   */
  @NonNull
  private Range selectionRange;

  /**
   * A data entry field that is preserved between a type hierarchy prepare and
   * supertypes or subtypes requests. It could also be used to identify the
   * type hierarchy in the server, helping improve the performance on
   * resolving supertypes and subtypes.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object data;

  public TypeHierarchyItem(@NonNull final String name, @NonNull final SymbolKind kind, @NonNull final String uri, @NonNull final Range range, @NonNull final Range selectionRange) {
    this.name = Preconditions.<String>checkNotNull(name, "name");
    this.kind = Preconditions.<SymbolKind>checkNotNull(kind, "kind");
    this.uri = Preconditions.<String>checkNotNull(uri, "uri");
    this.range = Preconditions.<Range>checkNotNull(range, "range");
    this.selectionRange = Preconditions.<Range>checkNotNull(selectionRange, "selectionRange");
  }

  public TypeHierarchyItem(@NonNull final String name, @NonNull final SymbolKind kind, @NonNull final String uri, @NonNull final Range range, @NonNull final Range selectionRange, final String detail) {
    this(name, kind, uri, range, selectionRange);
    this.detail = detail;
  }

  /**
   * The name of this item.
   */
  @NonNull
  public String getName() {
    return this.name;
  }

  /**
   * The name of this item.
   */
  public void setName(@NonNull final String name) {
    this.name = Preconditions.checkNotNull(name, "name");
  }

  /**
   * More detail for this item, e.g. the signature of a function.
   */
  public String getDetail() {
    return this.detail;
  }

  /**
   * More detail for this item, e.g. the signature of a function.
   */
  public void setDetail(final String detail) {
    this.detail = detail;
  }

  /**
   * The kind of this item.
   */
  @NonNull
  public SymbolKind getKind() {
    return this.kind;
  }

  /**
   * The kind of this item.
   */
  public void setKind(@NonNull final SymbolKind kind) {
    this.kind = Preconditions.checkNotNull(kind, "kind");
  }

  /**
   * Tags for this item.
   */
  public List<SymbolTag> getTags() {
    return this.tags;
  }

  /**
   * Tags for this item.
   */
  public void setTags(final List<SymbolTag> tags) {
    this.tags = tags;
  }

  /**
   * The resource identifier of this item.
   */
  @NonNull
  public String getUri() {
    return this.uri;
  }

  /**
   * The resource identifier of this item.
   */
  public void setUri(@NonNull final String uri) {
    this.uri = Preconditions.checkNotNull(uri, "uri");
  }

  /**
   * The range enclosing this symbol not including leading/trailing whitespace
   * but everything else, e.g. comments and code.
   */
  @NonNull
  public Range getRange() {
    return this.range;
  }

  /**
   * The range enclosing this symbol not including leading/trailing whitespace
   * but everything else, e.g. comments and code.
   */
  public void setRange(@NonNull final Range range) {
    this.range = Preconditions.checkNotNull(range, "range");
  }

  /**
   * The range that should be selected and revealed when this symbol is being
   * picked, e.g. the name of a function. Must be contained by the {@link #range}.
   */
  @NonNull
  public Range getSelectionRange() {
    return this.selectionRange;
  }

  /**
   * The range that should be selected and revealed when this symbol is being
   * picked, e.g. the name of a function. Must be contained by the {@link #range}.
   */
  public void setSelectionRange(@NonNull final Range selectionRange) {
    this.selectionRange = Preconditions.checkNotNull(selectionRange, "selectionRange");
  }

  /**
   * A data entry field that is preserved between a type hierarchy prepare and
   * supertypes or subtypes requests. It could also be used to identify the
   * type hierarchy in the server, helping improve the performance on
   * resolving supertypes and subtypes.
   */
  public Object getData() {
    return this.data;
  }

  /**
   * A data entry field that is preserved between a type hierarchy prepare and
   * supertypes or subtypes requests. It could also be used to identify the
   * type hierarchy in the server, helping improve the performance on
   * resolving supertypes and subtypes.
   */
  public void setData(final Object data) {
    this.data = data;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("name", this.name);
    b.add("detail", this.detail);
    b.add("kind", this.kind);
    b.add("tags", this.tags);
    b.add("uri", this.uri);
    b.add("range", this.range);
    b.add("selectionRange", this.selectionRange);
    b.add("data", this.data);
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TypeHierarchyItem other = (TypeHierarchyItem) obj;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals(other.name))
      return false;
    if (this.detail == null) {
      if (other.detail != null)
        return false;
    } else if (!this.detail.equals(other.detail))
      return false;
    if (this.kind == null) {
      if (other.kind != null)
        return false;
    } else if (!this.kind.equals(other.kind))
      return false;
    if (this.tags == null) {
      if (other.tags != null)
        return false;
    } else if (!this.tags.equals(other.tags))
      return false;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    if (this.range == null) {
      if (other.range != null)
        return false;
    } else if (!this.range.equals(other.range))
      return false;
    if (this.selectionRange == null) {
      if (other.selectionRange != null)
        return false;
    } else if (!this.selectionRange.equals(other.selectionRange))
      return false;
    if (this.data == null) {
      if (other.data != null)
        return false;
    } else if (!this.data.equals(other.data))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.name== null) ? 0 : this.name.hashCode());
    result = prime * result + ((this.detail== null) ? 0 : this.detail.hashCode());
    result = prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
    result = prime * result + ((this.tags== null) ? 0 : this.tags.hashCode());
    result = prime * result + ((this.uri== null) ? 0 : this.uri.hashCode());
    result = prime * result + ((this.range== null) ? 0 : this.range.hashCode());
    result = prime * result + ((this.selectionRange== null) ? 0 : this.selectionRange.hashCode());
    return prime * result + ((this.data== null) ? 0 : this.data.hashCode());
  }
}
