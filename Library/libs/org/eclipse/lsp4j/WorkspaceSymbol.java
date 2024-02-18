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
import org.eclipse.lsp4j.adapters.WorkspaceSymbolLocationTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A special workspace symbol that supports locations without a range
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class WorkspaceSymbol {
  /**
   * The name of this symbol.
   */
  @NonNull
  private String name;

  /**
   * The kind of this symbol.
   */
  @NonNull
  private SymbolKind kind;

  /**
   * Tags for this completion item.
   */
  private List<SymbolTag> tags;

  /**
   * The location of this symbol. Whether a server is allowed to
   * return a location without a range depends on the client
   * capability {@link SymbolCapabilities#resolveSupport}.
   * <p>
   * See also {@link SymbolInformation#location}.
   */
  @NonNull
  @JsonAdapter(WorkspaceSymbolLocationTypeAdapter.class)
  private Either<Location, WorkspaceSymbolLocation> location;

  /**
   * The name of the symbol containing this symbol. This information is for
   * user interface purposes (e.g. to render a qualifier in the user interface
   * if necessary). It can't be used to re-infer a hierarchy for the document
   * symbols.
   */
  private String containerName;

  /**
   * A data entry field that is preserved on a workspace symbol between a
   * workspace symbol request and a workspace symbol resolve request.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object data;

  public WorkspaceSymbol() {
  }

  public WorkspaceSymbol(@NonNull final String name, @NonNull final SymbolKind kind, @NonNull final Either<Location, WorkspaceSymbolLocation> location) {
    this.name = Preconditions.<String>checkNotNull(name, "name");
    this.kind = Preconditions.<SymbolKind>checkNotNull(kind, "kind");
    this.location = Preconditions.<Either<Location, WorkspaceSymbolLocation>>checkNotNull(location, "location");
  }

  public WorkspaceSymbol(@NonNull final String name, @NonNull final SymbolKind kind, @NonNull final Either<Location, WorkspaceSymbolLocation> location, final String containerName) {
    this(name, kind, location);
    this.containerName = containerName;
  }

  /**
   * The name of this symbol.
   */
  @NonNull
  public String getName() {
    return this.name;
  }

  /**
   * The name of this symbol.
   */
  public void setName(@NonNull final String name) {
    this.name = Preconditions.checkNotNull(name, "name");
  }

  /**
   * The kind of this symbol.
   */
  @NonNull
  public SymbolKind getKind() {
    return this.kind;
  }

  /**
   * The kind of this symbol.
   */
  public void setKind(@NonNull final SymbolKind kind) {
    this.kind = Preconditions.checkNotNull(kind, "kind");
  }

  /**
   * Tags for this completion item.
   */
  public List<SymbolTag> getTags() {
    return this.tags;
  }

  /**
   * Tags for this completion item.
   */
  public void setTags(final List<SymbolTag> tags) {
    this.tags = tags;
  }

  /**
   * The location of this symbol. Whether a server is allowed to
   * return a location without a range depends on the client
   * capability {@link SymbolCapabilities#resolveSupport}.
   * <p>
   * See also {@link SymbolInformation#location}.
   */
  @NonNull
  public Either<Location, WorkspaceSymbolLocation> getLocation() {
    return this.location;
  }

  /**
   * The location of this symbol. Whether a server is allowed to
   * return a location without a range depends on the client
   * capability {@link SymbolCapabilities#resolveSupport}.
   * <p>
   * See also {@link SymbolInformation#location}.
   */
  public void setLocation(@NonNull final Either<Location, WorkspaceSymbolLocation> location) {
    this.location = Preconditions.checkNotNull(location, "location");
  }

  /**
   * The name of the symbol containing this symbol. This information is for
   * user interface purposes (e.g. to render a qualifier in the user interface
   * if necessary). It can't be used to re-infer a hierarchy for the document
   * symbols.
   */
  public String getContainerName() {
    return this.containerName;
  }

  /**
   * The name of the symbol containing this symbol. This information is for
   * user interface purposes (e.g. to render a qualifier in the user interface
   * if necessary). It can't be used to re-infer a hierarchy for the document
   * symbols.
   */
  public void setContainerName(final String containerName) {
    this.containerName = containerName;
  }

  /**
   * A data entry field that is preserved on a workspace symbol between a
   * workspace symbol request and a workspace symbol resolve request.
   */
  public Object getData() {
    return this.data;
  }

  /**
   * A data entry field that is preserved on a workspace symbol between a
   * workspace symbol request and a workspace symbol resolve request.
   */
  public void setData(final Object data) {
    this.data = data;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("name", this.name);
    b.add("kind", this.kind);
    b.add("tags", this.tags);
    b.add("location", this.location);
    b.add("containerName", this.containerName);
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
    WorkspaceSymbol other = (WorkspaceSymbol) obj;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals(other.name))
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
    if (this.location == null) {
      if (other.location != null)
        return false;
    } else if (!this.location.equals(other.location))
      return false;
    if (this.containerName == null) {
      if (other.containerName != null)
        return false;
    } else if (!this.containerName.equals(other.containerName))
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
    result = prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
    result = prime * result + ((this.tags== null) ? 0 : this.tags.hashCode());
    result = prime * result + ((this.location== null) ? 0 : this.location.hashCode());
    result = prime * result + ((this.containerName== null) ? 0 : this.containerName.hashCode());
    return prime * result + ((this.data== null) ? 0 : this.data.hashCode());
  }
}
