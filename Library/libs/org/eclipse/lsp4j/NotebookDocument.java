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
import java.util.ArrayList;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A notebook document.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocument {
  /**
   * The notebook document's URI.
   */
  @NonNull
  private String uri;

  /**
   * The type of the notebook.
   */
  @NonNull
  private String notebookType;

  /**
   * The version number of this document (it will increase after each
   * change, including undo/redo).
   */
  private int version;

  /**
   * Additional metadata stored with the notebook
   * document.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object metadata;

  /**
   * The cells of a notebook.
   */
  @NonNull
  private List<NotebookCell> cells;

  public NotebookDocument() {
    ArrayList<NotebookCell> _arrayList = new ArrayList<NotebookCell>();
    this.cells = _arrayList;
  }

  public NotebookDocument(@NonNull final String uri, @NonNull final String notebookType, final int version, @NonNull final List<NotebookCell> cells) {
    this.uri = Preconditions.<String>checkNotNull(uri, "uri");
    this.notebookType = Preconditions.<String>checkNotNull(notebookType, "notebookType");
    this.version = version;
    this.cells = Preconditions.<List<NotebookCell>>checkNotNull(cells, "cells");
  }

  /**
   * The notebook document's URI.
   */
  @NonNull
  public String getUri() {
    return this.uri;
  }

  /**
   * The notebook document's URI.
   */
  public void setUri(@NonNull final String uri) {
    this.uri = Preconditions.checkNotNull(uri, "uri");
  }

  /**
   * The type of the notebook.
   */
  @NonNull
  public String getNotebookType() {
    return this.notebookType;
  }

  /**
   * The type of the notebook.
   */
  public void setNotebookType(@NonNull final String notebookType) {
    this.notebookType = Preconditions.checkNotNull(notebookType, "notebookType");
  }

  /**
   * The version number of this document (it will increase after each
   * change, including undo/redo).
   */
  public int getVersion() {
    return this.version;
  }

  /**
   * The version number of this document (it will increase after each
   * change, including undo/redo).
   */
  public void setVersion(final int version) {
    this.version = version;
  }

  /**
   * Additional metadata stored with the notebook
   * document.
   */
  public Object getMetadata() {
    return this.metadata;
  }

  /**
   * Additional metadata stored with the notebook
   * document.
   */
  public void setMetadata(final Object metadata) {
    this.metadata = metadata;
  }

  /**
   * The cells of a notebook.
   */
  @NonNull
  public List<NotebookCell> getCells() {
    return this.cells;
  }

  /**
   * The cells of a notebook.
   */
  public void setCells(@NonNull final List<NotebookCell> cells) {
    this.cells = Preconditions.checkNotNull(cells, "cells");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("uri", this.uri);
    b.add("notebookType", this.notebookType);
    b.add("version", this.version);
    b.add("metadata", this.metadata);
    b.add("cells", this.cells);
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
    NotebookDocument other = (NotebookDocument) obj;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    if (this.notebookType == null) {
      if (other.notebookType != null)
        return false;
    } else if (!this.notebookType.equals(other.notebookType))
      return false;
    if (other.version != this.version)
      return false;
    if (this.metadata == null) {
      if (other.metadata != null)
        return false;
    } else if (!this.metadata.equals(other.metadata))
      return false;
    if (this.cells == null) {
      if (other.cells != null)
        return false;
    } else if (!this.cells.equals(other.cells))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.uri== null) ? 0 : this.uri.hashCode());
    result = prime * result + ((this.notebookType== null) ? 0 : this.notebookType.hashCode());
    result = prime * result + this.version;
    result = prime * result + ((this.metadata== null) ? 0 : this.metadata.hashCode());
    return prime * result + ((this.cells== null) ? 0 : this.cells.hashCode());
  }
}
